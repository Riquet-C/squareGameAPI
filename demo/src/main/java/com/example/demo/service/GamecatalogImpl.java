package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

@Service
public class GamecatalogImpl implements GameCatalog {

    private final TicTacToeGameFactory ticTacToeGameFactory;
    private final TaquinGameFactory taquinGameFactory;
    private final ConnectFourGameFactory cfGameFactory;

    GamecatalogImpl() {
        this.ticTacToeGameFactory = new TicTacToeGameFactory();
        this.taquinGameFactory = new TaquinGameFactory();
        this.cfGameFactory = new ConnectFourGameFactory();
    }

    @Override
    public Collection<String> getGameIdentifiers() {
        return List.of(ticTacToeGameFactory.getGameFactoryId(), taquinGameFactory.getGameFactoryId(), cfGameFactory.getGameFactoryId());
    }
}
