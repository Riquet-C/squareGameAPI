package com.example.demo.dao;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Map;

public interface GameDao {
    void add(Game game);
    Game get(String id);
    Map<String, Game> getAll();
    void removeById(String id);
}
