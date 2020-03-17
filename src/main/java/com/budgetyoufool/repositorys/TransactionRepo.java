package com.budgetyoufool.repositorys;

import com.budgetyoufool.entitis.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Long, Transaction> {
}
