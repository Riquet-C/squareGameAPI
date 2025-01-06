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
import java.util.stream.Stream;


@Service
public class GameServiceImpl implements GameService {

    private final List<GamePlugin> gamePlugins;
    private final GameDaoMemory gameDaoMemory;
    private GameDaoJpa gameDaoJpa;
    private PlayerDaoJpa playerDaoJpa;
    private TokensDaoJpa tokensDaoJpa;

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
            saveGame(game);
            gameDaoMemory.add(game);
            return game;
        }
        return null;
    }

    private void saveGame(Game game) {
        GamesEntity gamesEntity = new GamesEntity();

        gamesEntity.setGamesId(game.getId());
        gamesEntity.setBoardSize(game.getBoardSize());
        gamesEntity.setGameType(game.getFactoryId());
        gameDaoJpa.save(gamesEntity);

        for (UUID playerId : game.getPlayerIds()) {
            PlayersEntity playerEntity = new PlayersEntity();
            playerEntity.setPlayerId(playerId);
            playerDaoJpa.save(playerEntity);
        }

        for (Token token : game.getRemainingTokens()) {
            TokensEntity tokenEntity = new TokensEntity();
            tokenEntity.setTokenName(token.getName());
            tokenEntity.setPlayed(false);
            tokensDaoJpa.save(tokenEntity);
        }

        for (Token token : game.getRemovedTokens()){
            TokensEntity tokenEntity = new TokensEntity();
            tokenEntity.setTokenName(token.getName());
            tokenEntity.setPositionX(token.getPosition().x());
            tokenEntity.setPositionY(token.getPosition().y());
            tokenEntity.setPlayed(true);
            tokensDaoJpa.save(tokenEntity);
        }

    }


    @Override
    public Game getGame(String gameId) {
        return gameDaoMemory.get(gameId);
    }

    @Override
    public List<Game> getAllGames() {
        return gameDaoMemory.getAll();
    }

    @Override
    public void deleteGame(String gameId) {
        gameDaoMemory.removeById(gameId);
    }


    @Override
    public Game moveToken(String gameId, String tokenId, CellPosition cellPosition) throws InvalidPositionException {
        Game game = getGame(gameId);
        @NotNull Token token = getTokenToMove(game, tokenId);
        token.moveTo(cellPosition);
        return null;
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

