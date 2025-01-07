package com.example.demo.controller;

import com.example.demo.dto.GameCreationParams;
import com.example.demo.dto.GamesEntity;
import com.example.demo.service.GameService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;


@RestController
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/games")
    public Game createGame(@RequestBody GameCreationParams params, Locale locale) {
        return gameService.initializeGame(params.gameName, locale);
    }

    @GetMapping("/games/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable UUID gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    @GetMapping("/games")
    public List<UUID> getExistingGames() {
        return gameService.getAllGames();
    }

    @PutMapping("/games/{gameId}/tokens/{tokenId}/position")
    public Object moveToken(@PathVariable String gameId, @PathVariable String tokenId, @RequestBody GameCreationParams params) throws InvalidPositionException {
        CellPosition cellPosition = new CellPosition(params.x, params.y);
        return gameService.moveToken(gameId, tokenId, cellPosition);
    }

    @DeleteMapping("/games/{gameId}")
    public void deleteGame(@PathVariable UUID gameId) {
        gameService.deleteGame(gameId);
    }


}
