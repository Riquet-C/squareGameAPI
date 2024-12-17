package com.example.demo.controller;

import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidPositionException.class)
    public ResponseEntity<String> handleInvalidPositionException(InvalidPositionException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Position invalid" + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Une erreur est survenue" + ex.getMessage());
    }
}
