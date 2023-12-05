package com.example.demo.Exception;

public class RegistrationException extends RuntimeException {
    public RegistrationException() {
        super("Email has been registered!");
    }
}
