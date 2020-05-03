package com.budgetyoufool.service.summingValues;

import com.budgetyoufool.model.DTO.TimeRangeDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SummingService {

    List<BigDecimal> sumDailyTransactions(LocalDate date);

    List<BigDecimal> sumOfMonthlyTransactions(LocalDate date);

    List<BigDecimal> sumOfTransactionsInTimeRange(TimeRangeDTO date);

}
