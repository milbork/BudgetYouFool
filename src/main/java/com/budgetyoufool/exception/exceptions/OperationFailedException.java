package com.budgetyoufool.exception.exceptions;

import java.time.LocalDateTime;

public class OperationFailedException extends RuntimeException {

    public OperationFailedException(String name, LocalDateTime time) {
        super(String.format("Operation in %s failed at %s", name, time.toString()));
    }
}
