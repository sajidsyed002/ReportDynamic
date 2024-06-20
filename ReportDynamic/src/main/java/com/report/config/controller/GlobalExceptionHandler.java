package com.report.config.controller;

import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleCustomException(Exception ex) {
       // ErrorResponse errorResponse = new ErrorResponse(ex.getMessage()) ;
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

   /* @ExceptionHandler(value = ArithmeticException.class)
    public ResponseEntity<Object> handleAnotherCustomException(ArithmeticException ex) {
        //ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }*/
}
