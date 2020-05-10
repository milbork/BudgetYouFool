package com.budgetyoufool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class DateAdvice {


    @ExceptionHandler(DateException.class)
    public ResponseEntity<String> bookNotFoundHandler(DateException ex) {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(ex.getMessage());
    }
}
