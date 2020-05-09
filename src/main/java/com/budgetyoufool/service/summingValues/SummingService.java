package com.budgetyoufool.service.summingValues;

import com.budgetyoufool.model.DTO.TransactionSummingDTO;
import java.time.LocalDate;

public interface SummingService {

    TransactionSummingDTO sumDailyTransactions(LocalDate date);

    TransactionSummingDTO sumOfMonthlyTransactions(LocalDate date);

    TransactionSummingDTO sumOfTransactionsInTimeRange(LocalDate start, LocalDate end);

}
