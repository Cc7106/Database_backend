package com.example.demo.Exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException() {
        super("CarId not exists!");
    }
}
