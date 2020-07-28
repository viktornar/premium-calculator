package com.github.viktornar.services;

public class NoCustomerException extends Exception {
    public NoCustomerException(String errorMessage) {
        super(errorMessage);
    }
}
