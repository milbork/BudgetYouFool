package com.budgetyoufool.service.summingValues;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SummingService {

    List<BigDecimal> showSumOfDailyTransactions(LocalDate date);

    List<BigDecimal> showSumOfMonthlyTransactions(LocalDate date);

    List<BigDecimal> showSumOfTransactionsInTimeRange(LocalDate startDate, LocalDate endDate);

}
