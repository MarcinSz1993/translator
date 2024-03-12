package com.example.translator.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("User with the username: " + username + " does not exist!");
    }
}
