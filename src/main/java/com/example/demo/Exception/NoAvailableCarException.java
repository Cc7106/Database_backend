package com.example.demo.Exception;

public class NoAvailableCarException extends RuntimeException {
    public NoAvailableCarException() {
        super("NO VEHICLES AVAILABLE AT SELECTED TIME!");
    }
}
