package com.budgetyoufool.repositorys;

import com.budgetyoufool.entitis.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
