package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;
import java.util.UUID;

@Entity
public class TokensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayersEntity player;

    private boolean isPlayed;

    public void setOwnerId(Optional<UUID> ownerId) {
        this.ownerId = ownerId.orElse(null);
    }

    private UUID ownerId;

    private Integer positionX;

    private Integer positionY;

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

    public UUID getOwnerId() {
        return ownerId;
    }
}

