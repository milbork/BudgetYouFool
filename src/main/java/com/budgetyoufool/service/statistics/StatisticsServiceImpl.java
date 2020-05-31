package com.budgetyoufool.service.statistics;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.service.grupingTransactions.GroupingService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import java.util.Map;


@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final GroupingService service;

    StatisticsServiceImpl(GroupingService service) {
        this.service = service;
    }


    public Map<IncomeTypeEnum, BigDecimal> getIncomeStatsByDateAndType(LocalDate start, LocalDate end) {

        Map<IncomeTypeEnum, BigDecimal> incomeEnumMap = new HashMap<>();

        for (IncomeTypeEnum e : IncomeTypeEnum.values()) {

            BigDecimal incomeEnum = service.getListOfIncomesInTimeRange(start, end)
                    .stream()
                    .filter(t -> t.getIncomeTypeEnum().equals(e))
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            incomeEnumMap.put(e, incomeEnum);
        }

        return incomeEnumMap;
    }

    public Map<OutcomeTypeEnum, BigDecimal> getOutcomeStatsByDateAndType(LocalDate start, LocalDate end) {

        Map<OutcomeTypeEnum, BigDecimal> outcomeEnumMap = new HashMap<>();

        for (OutcomeTypeEnum e : OutcomeTypeEnum.values()) {

            BigDecimal outcomeEnum = service.getListOfIncomesInTimeRange(start, end)
                    .stream()
                    .filter(t -> t.getOutcomeTypeEnum().equals(e))
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            outcomeEnumMap.put(e, outcomeEnum);
        }

        return outcomeEnumMap;
    }

}
