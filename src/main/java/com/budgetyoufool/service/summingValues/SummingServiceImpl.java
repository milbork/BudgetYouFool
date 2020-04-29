package com.budgetyoufool.service.summingValues;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;
import com.budgetyoufool.service.grupingTransactions.GroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SummingServiceImpl implements SummingService {

    TransactionRepo transactionRepo;
    GroupingService groupingService;

    @Autowired
    public SummingServiceImpl(TransactionRepo transactionRepo, GroupingService groupingService) {
        this.transactionRepo = transactionRepo;
        this.groupingService = groupingService;
    }


    @Override
    public List<BigDecimal> showSumOfDailyTransactions(LocalDate date) {

        List<BigDecimal> result = new ArrayList<>(2);

        BigDecimal incomeByDay = addTransactions(transactionRepo.findAllByDateAndIncomeTypeEnumNotNull(date));
        BigDecimal outcomeByDay = addTransactions(transactionRepo.findAllByDateAndOutcomeTypeEnumNotNull(date));
        result.add(incomeByDay);
        result.add(outcomeByDay);

        return result;
    }

    @Override
    public List<BigDecimal> showSumOfMonthlyTransactions(LocalDate date) {

        int start = 1;
        int end = date.lengthOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();

        LocalDate startDate = LocalDate.of(year, month, start);
        LocalDate endDate = LocalDate.of(year, month, end);

        return getIncomeAndOutcomeInTimeRange(startDate, endDate);
    }

    @Override
    public List<BigDecimal> showSumOfTransactionsInTimeRange(LocalDate startDate, LocalDate endDate) {

        return getIncomeAndOutcomeInTimeRange(startDate, endDate);
    }

    private BigDecimal addTransactions(List<Transaction> list) {

        return list.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<BigDecimal> getIncomeAndOutcomeInTimeRange(LocalDate startDate, LocalDate endDate) {

        List<Transaction> incomeByMonth = groupingService.getListOfIncomesInTimeRange(startDate, endDate);
        List<Transaction> outcomeByMonth = groupingService.getListOfOutcomesInTimeRange(startDate, endDate);

        List<BigDecimal> result = new ArrayList<>();
        result.add(addTransactions(incomeByMonth));
        result.add(addTransactions(outcomeByMonth));

        return result;
    }


}
