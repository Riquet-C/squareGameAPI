package com.example.demo.exceptions;

public class InvalideUserException extends RuntimeException {
    public InvalideUserException(String message) {
        super(message);
    }
}
