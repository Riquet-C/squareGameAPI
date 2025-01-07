package com.example.demo.dao.inMemoryGame;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryGameDaoMemory implements GameDaoMemory {
    private final Map<String, Game> games = new HashMap<>();

    @Override
    public void add(Game game) {
        games.put(game.getId().toString(), game);
    }

    @Override
    public Game findById(String id) {
        return games.get(id);
    }

    @Override
    public List<Game> getAll() {
        return games.values().stream().toList();
    }

    @Override
    public void removeById(String gameId) {
        games.remove(gameId);
    }

}
