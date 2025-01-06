package com.example.demo.dto;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class TokensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false) // Nom explicite pour la colonne de clé étrangère
    private PlayersEntity player;

    private boolean isPlayed;

    private int positionX;

    private int positionY;

    private String tokenName;

    // Getters et setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

