package com.budgetyoufool.exception.exceptions;

public class DateMismatchException extends RuntimeException {

    public DateMismatchException(){
        super("End date can't be before start!");
    }
}
