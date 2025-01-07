package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;
import java.util.UUID;

@Entity
public class TokensEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayersEntity player;

    private boolean isPlayed;

    private int positionX;

    private int positionY;

    private String tokenName;

    public void setGame(GamesEntity game) {
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name = "games_id", referencedColumnName = "id")
    private GamesEntity game;

    // Getters et setters
    public UUID getId() {
        return id;
    }

    public void setId(@NotNull Optional<UUID> id) {
        this.id = id.orElse(null);
    }

    public PlayersEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayersEntity player) {
        this.player = player;
    }

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }
}

