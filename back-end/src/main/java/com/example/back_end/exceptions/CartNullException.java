package com.example.back_end.exceptions;

public class CartNullException extends RuntimeException {
    public CartNullException(String message) {
        super(message);
    }
}
