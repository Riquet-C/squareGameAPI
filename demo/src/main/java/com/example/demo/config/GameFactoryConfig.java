package com.example.demo.config;

import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GameFactoryConfig {
    @Bean
    public TicTacToeGameFactory ticTacToeGameFactory() {
        return new TicTacToeGameFactory();
    }

    @Bean
    public TaquinGameFactory taquinGameFactory(){
        return new TaquinGameFactory();
    }

    @Bean
    public ConnectFourGameFactory cfGameFactory(){
        return new ConnectFourGameFactory();
    }
}
