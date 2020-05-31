package com.budgetyoufool.service.statistics;

import com.budgetyoufool.model.DTO.TransactionDTO;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;
import com.budgetyoufool.service.grupingTransactions.GroupingService;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;

import java.util.Map;


@Service
public class StatisticsServiceImpl implements StatisticsService{

    private final TransactionRepo repository;
    private final GroupingService service;

    StatisticsServiceImpl(TransactionRepo repository, GroupingService service){
        this.repository = repository;
        this.service = service;
    }


    public Map<IncomeTypeEnum, BigDecimal> getIncomeStatsByDateAndType(LocalDate start, LocalDate end){

        Map<IncomeTypeEnum, BigDecimal> incomeEnumMap = new HashMap<>();

        for (IncomeTypeEnum e : IncomeTypeEnum.values()){
            BigDecimal incomeEnum = service.getListOfIncomesInTimeRange(start, end)
                    .stream()
                    .filter(t -> t.getIncomeTypeEnum().equals(e))
                    .map(Transaction::getAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            incomeEnumMap.put(e, incomeEnum);
        }

        return incomeEnumMap;
    }

}
