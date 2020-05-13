package com.budgetyoufool.exception.exceptions;

public class TransactionTypeException extends RuntimeException {

    public TransactionTypeException() {
        super("Transaction must have only one selected type!");
    }
}
