package com.budgetyoufool.exception.exceptions;

import java.io.PrintStream;

public class DateException extends IllegalArgumentException {

    public DateException(){
        super("Date can't be in future!");
    }
}
