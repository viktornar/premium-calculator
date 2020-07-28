package com.github.viktornar.services;

public class NoCardException extends Exception {
    public NoCardException(String errorMessage) {
        super(errorMessage);
    }
}
