package com.example.demo.service;

import com.example.demo.dao.inMemoryGame.GameDaoMemory;
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

    public GameServiceImpl(List<GamePlugin> gamePlugins, GameDaoMemory gameDaoMemory) {
        this.gamePlugins = gamePlugins;
        this.gameDaoMemory = gameDaoMemory;
    }

    @Override
    public Game initializeGame(String gameName, Locale locale) {
        Optional<Game> gameOptional = gamePlugins.stream()
                .filter(item -> item.getName(locale).equals(gameName))
                .findFirst()
                .map(GamePlugin::createGame);
        if (gameOptional.isPresent()) {
            Game game = gameOptional.get();
            gameDaoMemory.add(game);
            return game;
        }
        return null;
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

