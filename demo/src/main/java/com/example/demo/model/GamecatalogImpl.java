package com.example.demo.model;

import java.util.Collection;
import java.util.List;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

@Service
public class GamecatalogImpl implements GameCatalog {

    private final TicTacToeGameFactory ticTacToeGameFactory;

    GamecatalogImpl() {
        this.ticTacToeGameFactory = new TicTacToeGameFactory();
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameFactoryId());
    }
}
