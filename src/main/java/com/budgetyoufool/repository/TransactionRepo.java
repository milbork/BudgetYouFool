package com.budgetyoufool.repository;

import com.budgetyoufool.model.transaction.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByDateAndIncomeTypeEnumNotNull(LocalDate date);

    List<Transaction> findAllByDateAndOutcomeTypeEnumNotNull(LocalDate date);

    List<Transaction> findAllByDateBetween(LocalDate start, LocalDate end);

    List<Transaction> findAllByDateEquals(LocalDate date);
}
