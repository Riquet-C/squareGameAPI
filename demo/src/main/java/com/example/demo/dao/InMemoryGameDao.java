package com.example.demo.dao;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryGameDao implements GameDao {
    private final Map<String, Game> games = new HashMap<>();

    @Override
    public void add(Game game) {
        games.put(game.getId().toString(), game);
    }

    @Override
    public Game get(String gameID) {
        return games.get(gameID);
    }

    @Override
    public Map<String, Game> getAll() {
        return games;
    }

    @Override
    public void removeById(String gameId) {
        games.remove(gameId);
    }


}
