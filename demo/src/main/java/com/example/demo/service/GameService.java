package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;

import java.util.List;
import java.util.Locale;

public interface GameService {
    Game initializeGame(String gameName, Locale locale);
    Game getGame(String gameId);
    List<Game> getAllGames();
    void deleteGame(String gameId);
    Object moveToken(String gameId, String tokenId, CellPosition cellPosition) throws InvalidPositionException;
}
