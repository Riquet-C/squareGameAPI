package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InconsistentGameDefinitionException;
import fr.le_campus_numerique.square_games.engine.TokenPosition;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class ConnectFourPlugin implements GamePlugin{

    private final ConnectFourGameFactory cfGameFactory;
    private final MessageSource messageSource;

    @Value("${game.connect4.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.connect4.default-board-size}")
    private int defaultBoardSize;

    public ConnectFourPlugin(ConnectFourGameFactory cfGameFactory, MessageSource messageSource) {
        this.cfGameFactory = cfGameFactory;
        this.messageSource = messageSource;
    }

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connect4.name", null, locale);
    }

    @Override
    public Game createGame() {
        return cfGameFactory.createGame(defaultPlayerCount, defaultBoardSize);
    }

    @Override
    public Game createGameWithId(UUID gameId, int boardSize, List<UUID> players, Collection<TokenPosition<UUID>> boardTokens, Collection<TokenPosition<UUID>> removedTokens) throws InconsistentGameDefinitionException {
        return cfGameFactory.createGameWithIds(gameId, boardSize, players, boardTokens, removedTokens);
    }
}
