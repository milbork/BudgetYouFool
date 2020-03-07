package com.budgetyoufool.repository;

import com.budgetyoufool.entitis.Transaction;
import com.budgetyoufool.entitis.TransactionLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepo extends JpaRepository<Transaction, Long> {

    public List<Transaction> findAllByTransactionLabel(TransactionLabel label) {
        return null;
    }
}
