package com.example.demo.Exception;

public class LoginException extends RuntimeException {
    public LoginException() {
        super("EMAIL OR PASSWORD INVALID");
    }
}
