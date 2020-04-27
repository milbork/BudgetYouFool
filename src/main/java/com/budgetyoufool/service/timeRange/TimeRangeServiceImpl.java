package com.budgetyoufool.service.timeRange;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeRangeServiceImpl implements TimeRangeService {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TimeRangeServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
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

    private List<Transaction> getListOfIncomesInTimeRange(LocalDate startDate, LocalDate endDate) {

        return transactionRepo.findAllByDateBetween(startDate.minusDays(1L), endDate.plusDays(1L))
                .stream()
                .filter(t -> t.getIncomeTypeEnum() != null)
                .collect(Collectors.toList()
                );
    }

    private List<Transaction> getListOfOutcomesInTimeRange(LocalDate startDate, LocalDate endDate) {

        return transactionRepo.findAllByDateBetween(startDate.minusDays(1L), endDate.plusDays(1L))
                .stream()
                .filter(t -> t.getOutcomeTypeEnum() != null)
                .collect(Collectors.toList()
                );
    }

    private List<BigDecimal> getIncomeAndOutcomeInTimeRange(LocalDate startDate, LocalDate endDate){

        List<Transaction> incomeByMonth = getListOfIncomesInTimeRange(startDate, endDate);
        List<Transaction> outcomeByMonth = getListOfOutcomesInTimeRange(startDate, endDate);

        ArrayList<BigDecimal> result = new ArrayList<>();
        result.add(addTransactions(incomeByMonth));
        result.add(addTransactions(outcomeByMonth));

        return result;
    }
 }
