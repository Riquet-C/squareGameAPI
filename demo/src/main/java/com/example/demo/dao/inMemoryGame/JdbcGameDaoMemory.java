package com.example.demo.dao.inMemoryGame;

import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcGameDaoMemory implements GameDaoMemory {

    NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcGameDaoMemory(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Game game) {
        String sql = "INSERT INTO games (uuid, name, boardSize) VALUES (:uuid, :name, :boardSize)";
        Map<String, Object> params = new HashMap<>();
        params.put("uuid", game.getId().toString());
        params.put("name", game.getFactoryId());
        params.put("boardSize", game.getBoardSize());
        jdbcTemplate.update(sql, params);
    }

    @Override
    public Game findById(String id) {
        String sql = "SELECT uuid, name, boardSize FROM games WHERE uuid = :uuid";
        Map<String, Object> params = new HashMap<>();
        params.put("uuid", id);
        return null;
    }

    private Map<String, Object> mapRowToGameDetails(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> result = new HashMap<>();
        result.put("uuid", rs.getString("uuid"));
        result.put("name", rs.getString("name"));
        result.put("boardSize", rs.getInt("boardSize"));
        return result;
    }

    @Override
    public List<Game> getAll() {
        return null;
    }

    @Override
    public void removeById(String id) {
    }
}
