package com.iordache.exception.error;

public class CarNotFoundException extends RuntimeException{

    public CarNotFoundException() {
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
