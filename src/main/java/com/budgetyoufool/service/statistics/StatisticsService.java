package com.budgetyoufool.service.statistics;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface StatisticsService {
    Map<IncomeTypeEnum, BigDecimal> getIncomeStatsByDateAndType(LocalDate start, LocalDate end);
    Map<OutcomeTypeEnum, BigDecimal> getOutcomeStatsByDateAndType(LocalDate start, LocalDate end);
}
