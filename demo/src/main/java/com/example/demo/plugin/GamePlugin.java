package com.example.demo.plugin;

import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Locale;

public interface GamePlugin {
    String getName(Locale locale);
    Game createGame();
}
