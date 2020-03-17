package com.budgetyoufool.services;

import com.budgetyoufool.interfaces.TransactionInterface;
import com.budgetyoufool.repositorys.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements TransactionInterface {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }


    @Override
    public void createTransaction() {

    }

    @Override
    public void readTransaction() {

    }

    @Override
    public void updateTransaction() {

    }

    @Override
    public void deleteTransaction() {

    }
}
