package com.budgetyoufool.exceptions;

public class DateException extends IllegalArgumentException {

    public DateException(){
        super("Date can't be in future!");
    }
}
