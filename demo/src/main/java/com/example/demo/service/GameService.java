package com.example.demo.service;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface GameService {
    Game initializeGame(String gameName, Locale locale);
    Game getGame(UUID gameId);
    List<UUID> getAllGames();
    void deleteGame(UUID gameId);
    Object moveToken(String gameId, String tokenId, CellPosition cellPosition) throws InvalidPositionException;
}
