package com.example.demo.plugin;

import com.example.demo.dto.PlayersEntity;
import com.example.demo.dto.TokensEntity;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InconsistentGameDefinitionException;
import fr.le_campus_numerique.square_games.engine.TokenPosition;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public interface GamePlugin {
    String getName(Locale locale);
    Game createGame();
    Game createGameWithId( UUID gameId,
                           int boardSize,
                           @NotEmpty List<UUID> players,
                           @NotNull Collection<TokenPosition<UUID>> boardTokens,
                           @NotNull Collection<TokenPosition<UUID>> removedTokens) throws InconsistentGameDefinitionException;
}
