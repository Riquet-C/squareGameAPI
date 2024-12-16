package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.List;
import java.util.Map;

public interface GameService {
    public Game initializeGame(String gameName, int playerCount, int boardSize);
    public Game getGame(String gameId);

    Map<String, Game> getAllGames();
}
