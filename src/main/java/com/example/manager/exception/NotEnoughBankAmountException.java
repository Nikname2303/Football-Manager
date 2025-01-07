package com.example.manager.exception;

public class NotEnoughBankAmountException extends RuntimeException {
    public NotEnoughBankAmountException(String message) {
        super(message);
    }
}
