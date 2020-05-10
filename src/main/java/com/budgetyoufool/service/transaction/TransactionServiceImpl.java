package com.budgetyoufool.service.transaction;

import com.budgetyoufool.model.DTO.TransactionDTO;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {

        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        Transaction save = transactionRepo.save(transaction);

        return modelMapper.map(save, TransactionDTO.class);
    }

    public void readTransaction() {
    }

    public void updateTransaction() {

    }

    public void deleteTransaction() {

    }
}
