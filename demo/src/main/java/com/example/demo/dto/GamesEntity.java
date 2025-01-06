package com.example.demo.dto;

import jakarta.persistence.*;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Entity
public class GamesEntity {

    @Id
    @Column(nullable = false, unique = true)
    private UUID gamesId;

    @Column(unique = true, nullable = false)
    private String gameType;

    @Column(nullable = false)
    private int boardSize;

    @OneToMany(mappedBy = "game",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlayersEntity> players = new HashSet<>();

    // Getters et setters
    public UUID getGamesId() {
        return gamesId;
    }

    public void setGamesId(UUID gamesId) {
        this.gamesId = gamesId;
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

    public Set<PlayersEntity> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayersEntity> players) {
        this.players = players;
    }
}
