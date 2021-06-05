package com.iordache.exception.error;

public class CarAlreadyExists extends RuntimeException{

    public CarAlreadyExists() {
    }

    public CarAlreadyExists(String message) {
        super(message);
    }
}
