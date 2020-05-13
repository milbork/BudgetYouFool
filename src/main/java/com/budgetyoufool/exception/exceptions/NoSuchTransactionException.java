package com.budgetyoufool.exception.exceptions;

public class NoSuchTransactionException extends RuntimeException {

    public NoSuchTransactionException() {
        super("There is no such transaction!");
    }

}
