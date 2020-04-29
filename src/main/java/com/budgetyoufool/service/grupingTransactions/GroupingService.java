package com.budgetyoufool.service.grupingTransactions;

import com.budgetyoufool.model.transaction.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface GroupingService {

    List<Transaction> getTransactionsListByDate(LocalDate date);

    List<Transaction> getTransactionsListByTimeRange(LocalDate start, LocalDate end);

    List<Transaction> getTransactionsListByMonth(LocalDate date);

    List<Transaction> getListOfIncomesInTimeRange(LocalDate startDate, LocalDate endDate);

    List<Transaction> getListOfOutcomesInTimeRange(LocalDate startDate, LocalDate endDate);
}
