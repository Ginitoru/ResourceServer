package com.iordache.web.exceptionHandler;

import com.iordache.exception.error.CarAlreadyExists;
import com.iordache.exception.response.CarErrorResponse;
import com.iordache.exception.error.CarNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CarExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CarErrorResponse> handleException(CarNotFoundException e){
        CarErrorResponse error = new CarErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        e.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CarErrorResponse> allExceptions(NumberFormatException e){
        CarErrorResponse error = new CarErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("NumberFormatException " + e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        e.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<CarErrorResponse> handleCarAlreadyExistsException(CarAlreadyExists e){
        CarErrorResponse error = new CarErrorResponse();

        error.setStatus(HttpStatus.FOUND.value());
        error.setMessage(e.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        e.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.FOUND);
    }

}
