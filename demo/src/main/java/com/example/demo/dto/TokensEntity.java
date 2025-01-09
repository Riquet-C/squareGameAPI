package com.example.demo.dto;

import jakarta.persistence.*;
import java.util.Optional;
import java.util.UUID;

@Entity
public class TokensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;


    private boolean isPlayed;

    public void setOwnerId(Optional<UUID> ownerId) {
        this.ownerId = ownerId.orElse(null);
    }

    private UUID ownerId;

    private Integer positionX;

    private Integer positionY;

    private String tokenName;

    // Getters et setters
    public UUID getId() {
        return id;
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

