package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InconsistentGameDefinitionException;
import fr.le_campus_numerique.square_games.engine.TokenPosition;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class TaquinPlugin implements GamePlugin {

    private final TaquinGameFactory taquinGameFactory;
    private final MessageSource messageSource;

    @Value("${game.taquin.default-player-count}")
    private int defaultPlayerCount;

    @Value("${game.taquin.default-board-size}")
    private int defaultBoardSize;

    public TaquinPlugin(TaquinGameFactory taquinGameFactory, MessageSource messageSource) {
        this.taquinGameFactory = taquinGameFactory;
        this.messageSource = messageSource;
    }

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.name", null, locale);
    }

    @Override
    public Game createGame() {
        return taquinGameFactory.createGame(defaultPlayerCount, defaultBoardSize);
    }



    @Override
    public Game createGameWithId(UUID gameId, int boardSize, List<UUID> players, Collection<TokenPosition<UUID>> boardTokens, Collection<TokenPosition<UUID>> removedTokens) throws InconsistentGameDefinitionException {
        return taquinGameFactory.createGameWithIds(gameId, boardSize, players, boardTokens, removedTokens);
    }
}
