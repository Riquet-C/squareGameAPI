package com.example.demo.dto;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class PlayersEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private GamesEntity game;


    // Getters et setters
    public UUID getPlayerId() {
        return id;
    }

    public void setPlayerId(UUID playerId) {
        this.id = playerId;
    }

    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }

}

