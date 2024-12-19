package com.example.demo.service;

import com.example.demo.dao.GameDao;
import com.example.demo.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Locale;
import java.util.*;
import java.util.stream.Stream;


@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;
    private final List<GamePlugin> gamePlugins;

    public GameServiceImpl(List<GamePlugin> gamePlugins) {
        this.gamePlugins = gamePlugins;
    }

    @Override
    public Game initializeGame(String gameName, Locale locale) {
        return gamePlugins.stream()
                .filter(item -> item.getName(locale).equals(gameName))
                .findFirst()
                .map(GamePlugin::createGame)
                .orElse(null); //si map est vide
    }

    @Override
    public Game getGame(String gameId){
        return gameDao.get(gameId);
    }

    @Override
    public Map<String, Game> getAllGames() {
        return gameDao.getAll();
    }

    @Override
    public void deleteGame(String gameId) {
        gameDao.removeById(gameId);
    }

    @Override
    public Object moveToken(String gameId, String tokenId, CellPosition cellPosition) throws InvalidPositionException {
        Game game = getGame(gameId);
        @NotNull Token token = getTokenToMove(game, tokenId);
        token.moveTo(cellPosition);
        return token.getPosition();
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

