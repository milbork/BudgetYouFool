package com.budgetyoufool.interfaces;

import com.budgetyoufool.DTO.*;

public interface TransactionInterface {

    public void createTransaction(TransactionDTO dto);

    public void readTransaction();

    public void updateTransaction();

    public void deleteTransaction();
}
