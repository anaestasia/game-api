package com.game.api.exception;

public class NameAlreadyTakenException extends RuntimeException {

    public NameAlreadyTakenException(String message) {
        super(message);
    }
}
