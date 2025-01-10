package com.example.demo.controller;

import com.example.demo.dto.GameCreationParams;
import com.example.demo.service.GameService;
import com.example.demo.service.UserValidationService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class GameController {

    private final GameService gameService;
    private final UserValidationService uservalidationService;

    public GameController(GameService gameService, UserValidationService uservalidationService) {
        this.gameService = gameService;
        this.uservalidationService = uservalidationService;
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

    @GetMapping("/finduser")
    public String startGame(@RequestHeader("X-userId") int userId) {
     return uservalidationService.validateUser(userId);
    }
}
