package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


@Service
public class GameServiceImpl implements GameService {

    private final Map<String, Game> games = new HashMap<>();

    @Override
    public Game initializeGame(String gameName, int playerCount, int boardSize) {
        GameFactory gameFactory = null;
        switch (gameName.toLowerCase()) {
            case ("tictactoe") -> gameFactory = new TicTacToeGameFactory();
            case ("15 puzzle") -> gameFactory =  new TaquinGameFactory();
            case ("connect4") -> gameFactory = new ConnectFourGameFactory();
        }
        assert gameFactory != null;
        Game game = gameFactory.createGame(playerCount, boardSize);
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

