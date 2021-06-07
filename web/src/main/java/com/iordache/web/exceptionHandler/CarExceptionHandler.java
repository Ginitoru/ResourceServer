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

    @ExceptionHandler //method 2
    public ResponseEntity<CarErrorResponse> handleCarNotFoundException(CarNotFoundException exc){

        var error = carErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), exc);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler //method 2
    public ResponseEntity<CarErrorResponse> allNumberFormatException(NumberFormatException exc){

        var message ="NumberFormatException " + exc.getMessage();
        var error = carErrorResponse(HttpStatus.BAD_REQUEST.value(), message, exc);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler //method 2
    public ResponseEntity<CarErrorResponse> handleCarAlreadyExistsException(CarAlreadyExists exc){

        var error = carErrorResponse(HttpStatus.FOUND.value(), exc.getMessage(), exc);
        return new ResponseEntity<>(error, HttpStatus.FOUND);
    }

    //method 1
    private CarErrorResponse carErrorResponse(int status, String message, RuntimeException exception){

        exception.printStackTrace();
        return new CarErrorResponse(status, message, System.currentTimeMillis());
    }

}
