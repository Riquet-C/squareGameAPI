package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public Game getGame(String gameId){
        return games.get(gameId);
    }

    @Override
    public Map<String, Game> getAllGames() {
        return games;
    }

}

