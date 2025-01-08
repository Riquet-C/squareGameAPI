package com.example.demo.service;

import com.example.demo.dao.inMemoryGame.GameDaoJpa;
import com.example.demo.dao.inMemoryGame.GameDaoMemory;
import com.example.demo.dao.inMemoryGame.PlayerDaoJpa;
import com.example.demo.dao.inMemoryGame.TokensDaoJpa;
import com.example.demo.dto.GamesEntity;
import com.example.demo.dto.PlayersEntity;
import com.example.demo.dto.TokensEntity;
import com.example.demo.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


@Service
public class GameServiceImpl implements GameService {

    private final List<GamePlugin> gamePlugins;
    private final GameDaoMemory gameDaoMemory;
    private final GameDaoJpa gameDaoJpa;
    private final PlayerDaoJpa playerDaoJpa;
    private final TokensDaoJpa tokensDaoJpa;

    public GameServiceImpl(List<GamePlugin> gamePlugins, GameDaoMemory gameDaoMemory, GameDaoJpa gameDaoJpa, PlayerDaoJpa playerDaoJpa, TokensDaoJpa tokensDaoJpa) {
        this.gamePlugins = gamePlugins;
        this.gameDaoJpa = gameDaoJpa;
        this.gameDaoMemory = gameDaoMemory;
        this.playerDaoJpa = playerDaoJpa;
        this.tokensDaoJpa = tokensDaoJpa;
    }

    @Override
    public Game initializeGame(String gameName, Locale locale) {
        Optional<Game> gameOptional = gamePlugins.stream()
                .filter(item -> item.getName(locale).equals(gameName))
                .map(GamePlugin::createGame)
                .findFirst();
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            saveGameData(game);
            gameDaoMemory.add(game);
            return game;
        }
        return null;
    }

    private List<UUID> findPlayersId(GamesEntity gamesEntity) {
        return  gamesEntity.getPlayers().stream()
                .map(PlayersEntity::getPlayerId)
                .toList();

    }

    private Collection<TokenPosition<UUID>> extractRemainingTokens(GamesEntity gamesEntity) {
        return gamesEntity.getTokens().stream()
                .filter(token -> token.isPlayed())
                .map(token -> new TokenPosition<>(token.getOwnerId(), token.getTokenName(), token.getPositionX(), token.getPositionY()))
                .toList();
    }

    private Collection<TokenPosition<UUID>> extractRemovedTokens(GamesEntity gamesEntity) {
        return List.of();
    }

    private Game createGameUsingPlugin(UUID gameId, Locale locale, String gameName, int boardSize,
                                       List<UUID> playersId, Collection<TokenPosition<UUID>> remainingTokensId,
                                       Collection<TokenPosition<UUID>> removedTokensId) {
        return gamePlugins.stream()
                .filter(gamePlugin -> Objects.equals(gamePlugin.getName(Locale.ENGLISH), gameName))
                .findFirst()
                .map(gamePlugin -> {
                    try {
                        return gamePlugin.createGameWithId(gameId, boardSize, playersId, remainingTokensId, removedTokensId);
                    } catch (InconsistentGameDefinitionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElse(null);
    }

    private Game createGameFromData(UUID gameId, Locale locale) {
        Optional<GamesEntity> gameDaoJpaById = gameDaoJpa.findById(gameId);

        GamesEntity gameEntity = gameDaoJpaById.get();
        String gameName = gameEntity.getGameType();
        int boardSize = gameEntity.getBoardSize();
        List<UUID> playersId = findPlayersId(gameEntity);
        Collection<TokenPosition<UUID>> extractRemovedTokens = extractRemovedTokens(gameEntity);
        Collection<TokenPosition<UUID>> extractRemainingTokens = extractRemainingTokens(gameEntity);

        return createGameUsingPlugin(gameId, locale, gameName, boardSize, playersId, extractRemovedTokens, extractRemainingTokens);
    }

    private void saveGameData(Game game) {
        GamesEntity gamesEntity = new GamesEntity();


        gamesEntity.setGamesId(game.getId());
        gamesEntity.setBoardSize(game.getBoardSize());
        gamesEntity.setGameType(game.getFactoryId());
        gameDaoJpa.save(gamesEntity);

        for (UUID playerId : game.getPlayerIds()) {
            PlayersEntity playerEntity = new PlayersEntity();
            playerEntity.setPlayerId(playerId);
            playerEntity.setGame(gamesEntity);
            playerDaoJpa.save(playerEntity);

            for (Token token : game.getRemovedTokens()){
                TokensEntity tokenEntity = new TokensEntity();
                tokenEntity.setOwnerId(token.getOwnerId());
                tokenEntity.setTokenName(token.getName());
                tokenEntity.setPositionX(token.getPosition().x());
                tokenEntity.setPositionY(token.getPosition().y());
                tokenEntity.setPlayed(true);
                tokenEntity.setPlayer(playerEntity);
                tokensDaoJpa.save(tokenEntity);
            }
        }

        for (Token token : game.getRemainingTokens()) {
            TokensEntity tokenEntity = new TokensEntity();
            tokenEntity.setOwnerId(token.getOwnerId());
            tokenEntity.setTokenName(token.getName());
            tokenEntity.setPlayed(false);
            tokenEntity.setGame(gamesEntity);
            tokensDaoJpa.save(tokenEntity);
        }
    }

    @Override
    public Game getGame(UUID gameId) {
       return createGameFromData(gameId, Locale.FRANCE);
    }

    public List<UUID> getAllGames() {
        return gameDaoJpa.findAll().stream()
                .map(GamesEntity::getGamesId)
                .toList();
    }

    @Override
    public void deleteGame(UUID gameId) {
        gameDaoMemory.removeById(gameId.toString());
    }

    @Override
    public Game moveToken(String gameId, String tokenId, CellPosition cellPosition) throws InvalidPositionException {
        Game game = getGame(UUID.fromString(gameId));
        @NotNull Token token = getTokenToMove(game, tokenId);
        token.moveTo(cellPosition);
        updateGameData(cellPosition, token.getName(), game.getCurrentPlayerId());
        return null;
    }

    private void updateGameData(CellPosition cellPosition, String tokenName, UUID playerId) {
        TokensEntity tokenEntity = tokensDaoJpa.findFirstByTokenName(tokenName);
        PlayersEntity playerEntity = playerDaoJpa.getReferenceById(playerId);
        if (tokenEntity != null){
            tokenEntity.setPositionX(cellPosition.x());
            tokenEntity.setPositionY(cellPosition.y());
            tokenEntity.setPlayer(playerEntity);
            tokenEntity.setPlayed(true);
        }
        assert tokenEntity != null;
        tokensDaoJpa.save(tokenEntity);
    }

    private Token getTokenToMove(Game game, String tokenId) {
        return Stream.of(game.getRemainingTokens(), game.getRemovedTokens(), game.getBoard().values())
                .flatMap(Collection::stream)
                .filter(token -> token.getName().equals(tokenId))
                .filter(Token::canMove)
                .findFirst()
                .orElse(null);
    }

}

