package com.example.demo.dao.inMemoryGame;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.List;
import java.util.Map;

public interface GameDaoMemory {
    void add(Game game);
    Game get(String id);
    List<Game> getAll();
    void removeById(String id);
}
