package com.example.exceptions;

public class UserDefinedException extends RuntimeException {

    UserDefinedException(String message) {
        super(message);
    }
}
