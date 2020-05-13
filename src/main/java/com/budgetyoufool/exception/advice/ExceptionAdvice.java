package com.budgetyoufool.exception.advice;

import com.budgetyoufool.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DateException.class)
    public ResponseEntity<String> dateInFutureHandler(DateException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(DateMismatchException.class)
    public ResponseEntity<String> dateMismatchHandler(DateMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(URIResponseException.class)
    public ResponseEntity<String> uriExceptionHandler(URIResponseException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchTransactionException.class)
    public ResponseEntity<String> noTransactionHandler(NoSuchTransactionException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<String> operationFailedHandler(OperationFailedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(TransactionTypeException .class)
    public ResponseEntity<String> TransactionTypeHandler(TransactionTypeException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
