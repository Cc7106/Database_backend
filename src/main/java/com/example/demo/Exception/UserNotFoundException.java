package com.example.demo.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not exists!");
    }
}
