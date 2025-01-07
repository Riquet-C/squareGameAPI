package com.example.demo.dto;

import jakarta.persistence.*;


import java.util.*;

@Entity
public class GamesEntity {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String gameType;

    @Column(nullable = false)
    private int boardSize;

    @OneToMany(mappedBy = "game",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayersEntity> players = new ArrayList<>();

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<TokensEntity> tokens = new ArrayList<>();

    // Getters et setters
    public UUID getGamesId() {
        return id;
    }

    public void setGamesId(UUID gamesId) {
        this.id = gamesId;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public List<PlayersEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayersEntity> players) {
        this.players = players;
    }

    public Collection<TokensEntity> getTokens() {
        return tokens;
    }

    public void setTokens(Collection<TokensEntity> tokens) {
        this.tokens = tokens;
    }
}
