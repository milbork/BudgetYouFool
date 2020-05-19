package com.budgetyoufool.exception.exceptions;

public class DateException extends RuntimeException {

    public DateException() {
        super("Date can't be in future!");
    }
}
