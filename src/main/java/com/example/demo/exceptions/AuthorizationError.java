package com.example.demo.exceptions;

public class AuthorizationError extends RuntimeException{

    String userName;

    public AuthorizationError(String message, String userName) {
        super(message);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
