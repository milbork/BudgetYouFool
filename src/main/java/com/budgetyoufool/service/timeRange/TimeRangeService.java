package com.budgetyoufool.service.timeRange;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TimeRangeService {

    List<BigDecimal> showSumOfDailyTransactions(LocalDate date);
    List<BigDecimal> showSumOfMonthlyTransactions(LocalDate date);
    List<BigDecimal> showSumOfTransactionsInTimeRange(LocalDate startDate, LocalDate endDate);

}
