package com.budgetyoufool.service.grupingTransactions;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "TransactionCache")
public class GroupingServiceImpl implements GroupingService {

    private final TransactionRepo transactionRepo;

    public GroupingServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }


    @Override
    public List<Transaction> getTransactionsListByDate(LocalDate date) {

        return transactionRepo.findAllByDateEquals(date);
    }

    @Override
    public List<Transaction> getTransactionsListByTimeRange(LocalDate start, LocalDate end) {

        return transactionRepo.findAllByDateBetween(start, end);
    }

    @Override
    public List<Transaction> getTransactionsListByMonth(LocalDate date) {

        LocalDate firstDay = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
        LocalDate lastDay = LocalDate.of(date.getYear(), date.getMonthValue(), date.lengthOfMonth());

        return transactionRepo.findAllByDateBetween(firstDay, lastDay);
    }

    @Override
    public List<Transaction> getListOfIncomesInTimeRange(LocalDate startDate, LocalDate endDate) {

        return transactionRepo.findAllByDateBetween(startDate, endDate)
                .stream()
                .filter(t -> t.getIncomeTypeEnum() != null)
                .collect(Collectors.toList()
                );
    }

    @Override
    public List<Transaction> getListOfOutcomesInTimeRange(LocalDate startDate, LocalDate endDate) {

        return transactionRepo.findAllByDateBetween(startDate, endDate)
                .stream()
                .filter(t -> t.getOutcomeTypeEnum() != null)
                .collect(Collectors.toList()
                );
    }
}
