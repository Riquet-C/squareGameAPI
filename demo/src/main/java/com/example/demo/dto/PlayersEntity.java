package com.example.demo.dto;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class PlayersEntity {

    @Id
    private UUID playerId;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false) // Nom explicite pour la colonne de clé étrangère
    private GamesEntity game;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TokensEntity> tokens;

    // Getters et setters
    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }

    public List<TokensEntity> getTokens() {
        return tokens;
    }

    public void setTokens(List<TokensEntity> tokens) {
        this.tokens = tokens;
    }
}

