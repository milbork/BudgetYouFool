package com.budgetyoufool.service.grupingTransactions;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
class GroupingServiceImplTest {

    private TransactionRepo repo;
    private GroupingServiceImpl groupingService;

    @BeforeEach
    void init() {
        repo = mock(TransactionRepo.class);
        groupingService = new GroupingServiceImpl(repo);
    }

    @Test
    void getTransactionsListByDate() {

        LocalDate date = LocalDate.of(2020, 4, 30);
        List<Transaction> mockList = transactionListInit()
                .stream()
                .filter(t -> t.getDate().equals(date))
                .collect(Collectors.toList());

        when(repo.findAllByDateEquals(date)).thenReturn(mockList);

        Assertions.assertIterableEquals(mockList, groupingService.getTransactionsListByDate(date));

    }

    @Test
    void getTransactionsListByTimeRange() {

        LocalDate start = LocalDate.of(2020, 4, 1);
        LocalDate end = LocalDate.of(2020, 4, 30);

        List<Transaction> mockList = transactionListInit()
                .stream()
                .filter(t -> t.getDate().isAfter(start.minus(Period.ofDays(1))) && t.getDate().isBefore(end.plus(Period.ofDays(1))))
                .collect(Collectors.toList());

        when(repo.findAllByDateBetween(start, end)).thenReturn(mockList);

        Assertions.assertIterableEquals(groupingService.getTransactionsListByTimeRange(start, end), mockList);

    }

    @Test
    void getTransactionsListByMonth() {
    }

    @Test
    void getListOfIncomesInTimeRange() {
    }

    @Test
    void getListOfOutcomesInTimeRange() {
    }

    private List<Transaction> transactionListInit() {

        List<Transaction> mockList = new ArrayList<>();

        mockList.add(new Transaction(
                BigDecimal.valueOf(25), "snack", LocalDate.of(2020, Month.APRIL, 13), null, IncomeTypeEnum.SALARY));
        mockList.add(new Transaction(
                BigDecimal.valueOf(35), "snack", LocalDate.of(2020, Month.APRIL, 30), OutcomeTypeEnum.BILLS, null));
        mockList.add(new Transaction(
                BigDecimal.valueOf(5), "snack", LocalDate.of(2020, Month.APRIL, 30), OutcomeTypeEnum.BILLS, null));
        mockList.add(new Transaction(
                BigDecimal.valueOf(25), "snack", LocalDate.of(2020, Month.APRIL, 30), null, IncomeTypeEnum.SALARY));

        return mockList;
    }
}
