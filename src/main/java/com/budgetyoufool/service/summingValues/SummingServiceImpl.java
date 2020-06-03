package com.budgetyoufool.service.summingValues;

import com.budgetyoufool.model.DTO.TransactionSummingDTO;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;
import com.budgetyoufool.service.grupingTransactions.GroupingService;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@CacheConfig(cacheNames = "SummingCache")
public class SummingServiceImpl implements SummingService {

    private final TransactionRepo transactionRepo;
    private final GroupingService groupingService;

    public SummingServiceImpl(TransactionRepo transactionRepo, GroupingService groupingService) {
        this.transactionRepo = transactionRepo;
        this.groupingService = groupingService;
    }


    @Override
    public TransactionSummingDTO sumDailyTransactions(LocalDate date) {

        TransactionSummingDTO result = new TransactionSummingDTO();

        result.setIncome(addTransactions(
                transactionRepo
                        .findAllByDateEquals(date)
                        .stream()
                        .filter(t -> t.getIncomeTypeEnum() != null)
                        .collect(Collectors.toList()))
        );

        result.setOutcome(addTransactions(
                transactionRepo
                        .findAllByDateEquals(date)
                        .stream()
                        .filter(t -> t.getOutcomeTypeEnum() != null)
                        .collect(Collectors.toList()))
        );

        return result;
    }

    @Override
    public TransactionSummingDTO sumOfMonthlyTransactions(LocalDate date) {

        int start = 1;
        int end = date.lengthOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        LocalDate startDate = LocalDate.of(year, month, start);
        LocalDate endDate = LocalDate.of(year, month, end);

        return getIncomeAndOutcomeInTimeRange(startDate, endDate);
    }

    @Override
    public TransactionSummingDTO sumOfTransactionsInTimeRange(LocalDate start, LocalDate end) {

        return getIncomeAndOutcomeInTimeRange(start, end);
    }

    private BigDecimal addTransactions(List<Transaction> list) {

        return list.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private TransactionSummingDTO getIncomeAndOutcomeInTimeRange(LocalDate startDate, LocalDate endDate) {

        List<Transaction> income = groupingService.getListOfIncomesInTimeRange(startDate, endDate);
        List<Transaction> outcome = groupingService.getListOfOutcomesInTimeRange(startDate, endDate);

        TransactionSummingDTO result = new TransactionSummingDTO();

        result.setIncome(addTransactions(income));
        result.setOutcome(addTransactions(outcome));
       
        return result;
    }


}

