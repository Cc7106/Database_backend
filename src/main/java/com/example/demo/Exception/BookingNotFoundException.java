package com.example.demo.Exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException() {
        super("Booking not exists!");
    }
}
