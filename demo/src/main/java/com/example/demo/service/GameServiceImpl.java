package com.example.demo.service;

import com.example.demo.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import java.util.Locale;
import java.util.*;
import java.util.stream.Stream;


@Service
public class GameServiceImpl implements GameService {

    private final Map<String, Game> games = new HashMap<>();
    private final Map<String, GamePlugin> plugins = new HashMap<>();

    public GameServiceImpl(List<GamePlugin> gamePlugins) {
        for (GamePlugin gamePlugin : gamePlugins) {
            plugins.put(gamePlugin.getName(Locale.FRANCE).toLowerCase(), gamePlugin);
        }
    }

    @Override
    public Game initializeGame(String gameName) {
        GamePlugin plugin = plugins.get(gameName.toLowerCase());
        Game game = plugin.createGame();
        games.put(game.getId().toString(), game);
        return games.get(game.getId().toString());
    }

    @Override
    public Game getGame(String gameId){
        return games.get(gameId);
    }

    @Override
    public Map<String, Game> getAllGames() {
        return games;
    }

    @Override
    public void deleteGame(String gameId) {
        games.remove(gameId);
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

