package com.skypro.controller;

import com.skypro.exception.EmployeeException;
import com.skypro.exception.EmployeeNotFoundedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleEmployeeException(EmployeeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmployeeNotFoundedException.class)
    public ResponseEntity<String> handleEmployeeNotFoundedException(EmployeeNotFoundedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
