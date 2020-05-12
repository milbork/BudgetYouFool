package com.budgetyoufool.service.transaction;

import com.budgetyoufool.model.DTO.TransactionDTO;

public interface TransactionService {

    TransactionDTO createTransaction(TransactionDTO transactionDTO);

    TransactionDTO showTransaction(Long id);

    void updateTransaction(TransactionDTO transactionDTO);

    boolean deleteTransaction(Long id);

}
