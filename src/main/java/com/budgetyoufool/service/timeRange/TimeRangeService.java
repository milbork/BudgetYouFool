package com.budgetyoufool.service.timeRange;

import com.budgetyoufool.model.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TimeRangeService {

    List<Transaction> getTransactionsListByDate(LocalDate date);
    List<BigDecimal> showSumOfDailyTransactions(LocalDate date);
    List<BigDecimal> showSumOfMonthlyTransactions(LocalDate date);
    List<BigDecimal> showSumOfTransactionsInTimeRange(LocalDate startDate, LocalDate endDate);
    List<Transaction> getTransactionsListByTimeRange(LocalDate start, LocalDate end);

}
