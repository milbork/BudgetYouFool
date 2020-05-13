package com.budgetyoufool.exception.exceptions;


public class OperationFailedException extends RuntimeException {

    public OperationFailedException(String name) {
        super(String.format("Operation in %s failed", name));
    }
}
