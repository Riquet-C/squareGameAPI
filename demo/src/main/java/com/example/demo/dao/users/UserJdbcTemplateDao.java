package com.example.demo.dao.users;

import com.example.demo.dto.UserEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Primary
public class UserJdbcTemplateDao implements UserDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserJdbcTemplateDao(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserEntity> findAll() {
        String sql = "SELECT id, userName, email FROM users";
        return jdbcTemplate.query(sql, this::mapRowToUser);
    }

    @Override
    public UserEntity findById(int id) {
        String sql = "SELECT id, userName, email FROM users WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.query(sql, params, this::mapRowToUser)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserEntity add(UserEntity user) {
        String sql = "INSERT INTO users (userName, email, password) VALUES (:name, :email, :password)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getUserName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});
        user.setId(Objects.requireNonNull(keyHolder.getKey()).intValue());
        return user;
    }

    @Override
    public UserEntity update(UserEntity user) {
        String sql = "UPDATE users SET userName = :name, email = :email WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getUserName())
                .addValue("email", user.getEmail())
                .addValue("id", user.getId());
        jdbcTemplate.update(sql, params);
        return user;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, params);
    }

    private UserEntity mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        UserEntity user = new UserEntity();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("userName"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}