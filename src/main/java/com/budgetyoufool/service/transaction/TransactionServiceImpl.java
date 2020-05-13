package com.budgetyoufool.service.transaction;

import com.budgetyoufool.exception.exceptions.NoSuchTransactionException;
import com.budgetyoufool.model.DTO.TransactionDTO;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    @Override
    public TransactionDTO showTransaction(Long id) {
        Transaction transaction = transactionRepo
                .findById(id)
                .orElseThrow(NoSuchTransactionException::new);

        return modelMapper.map(transaction, TransactionDTO.class);
    }

    @Override
    public void updateTransaction(TransactionDTO transactionDTO) {

        transactionRepo
                .findById(transactionDTO.getId())
                .orElseThrow(NoSuchTransactionException::new);

        Transaction transaction = modelMapper.map(transactionDTO, Transaction.class);
        transactionRepo.save(transaction);
    }

    @Override
    public boolean deleteTransaction(Long id) {

        transactionRepo
                .findById(id)
                .orElseThrow(NoSuchTransactionException::new);

        transactionRepo.deleteById(id);

        return transactionRepo.existsById(id);
    }
}
