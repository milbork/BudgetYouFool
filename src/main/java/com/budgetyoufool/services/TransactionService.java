package com.budgetyoufool.services;

import com.budgetyoufool.entitis.Transaction;
import com.budgetyoufool.interfaces.TransactionInterface;
import com.budgetyoufool.repositorys.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.budgetyoufool.DTO.*;

@Service
public class TransactionService implements TransactionInterface {

    private TransactionRepo transactionRepo;

    @Autowired
    public TransactionService(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }


    @Override
    public void createTransaction(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setName(dto.getName());
        transaction.setAmount(dto.getAmount());
        transaction.setDate(dto.getDate());
        transaction.setCategory(dto.getCategory());
        transactionRepo.save(transaction);
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
