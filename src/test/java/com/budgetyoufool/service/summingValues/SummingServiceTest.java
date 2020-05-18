package com.budgetyoufool.service.summingValues;

import com.budgetyoufool.model.DTO.TransactionSummingDTO;
import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.repository.TransactionRepo;
import com.budgetyoufool.service.grupingTransactions.GroupingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest
class SummingServiceTest {

    private SummingServiceImpl summingService;
    @MockBean
    private TransactionRepo repo;
    @MockBean
    private GroupingServiceImpl groupingService;

    @BeforeEach
    void init() {
        summingService = new SummingServiceImpl(repo, groupingService);
    }

    @Test
    void shouldPassIfReturnsCorrectAmountsOfDailyTransactions() {

        List<Transaction> mockList = transactionListInit()
                .stream()
                .filter(t -> t.getDate().equals(LocalDate.of(2020, Month.APRIL, 30)))
                .collect(Collectors.toList());

        when(repo.findAllByDateEquals(LocalDate.of(2020, Month.APRIL, 30)))
                .thenReturn(mockList);

        TransactionSummingDTO tsDTO = summingService.sumDailyTransactions(LocalDate.of(2020, Month.APRIL, 30));

        Assertions.assertTrue(tsDTO.getIncome().equals(BigDecimal.valueOf(25))
                && tsDTO.getOutcome().equals(BigDecimal.valueOf(40))
        );
    }

    @Test
    void shouldPassReturnsCorrectAmountsOfMonthlyTransactions() {

        List<Transaction> mockList = transactionListInit();

        when(repo.findAllByDateBetween(
                LocalDate.of(2020, Month.APRIL, 1),
                LocalDate.of(2020, Month.APRIL, 30)))
                .thenReturn(mockList);

        when(groupingService.getListOfIncomesInTimeRange(
                LocalDate.of(2020, Month.APRIL, 1),
                LocalDate.of(2020, Month.APRIL, 30)))
                .thenReturn(mockList
                        .stream()
                        .filter(t -> t.getIncomeTypeEnum() != null)
                        .collect(Collectors.toList()));

        when(groupingService.getListOfOutcomesInTimeRange(
                LocalDate.of(2020, Month.APRIL, 1),
                LocalDate.of(2020, Month.APRIL, 30)))
                .thenReturn(mockList
                        .stream()
                        .filter(t -> t.getOutcomeTypeEnum() != null)
                        .collect(Collectors.toList()));

        TransactionSummingDTO tsDTO = summingService.sumOfMonthlyTransactions(LocalDate.of(2020, Month.APRIL, 15));

        Assertions.assertTrue(tsDTO.getIncome().equals(BigDecimal.valueOf(50))
                && tsDTO.getOutcome().equals(BigDecimal.valueOf(40)));
    }

    @Test
    void shouldPassIfReturnsCorrectAmountsOfTransactionsInTimeRange() {

        List<Transaction> mockList = transactionListInit();

        when(repo.findAllByDateBetween(
                LocalDate.of(2020, Month.APRIL, 1),
                LocalDate.of(2020, Month.APRIL, 30)))
                .thenReturn(mockList);

        when(groupingService.getListOfIncomesInTimeRange(
                LocalDate.of(2020, Month.APRIL, 1),
                LocalDate.of(2020, Month.APRIL, 30)))
                .thenReturn(mockList
                        .stream()
                        .filter(t -> t.getIncomeTypeEnum() != null)
                        .collect(Collectors.toList()));

        when(groupingService.getListOfOutcomesInTimeRange(
                LocalDate.of(2020, Month.APRIL, 1),
                LocalDate.of(2020, Month.APRIL, 30)))
                .thenReturn(mockList
                        .stream()
                        .filter(t -> t.getOutcomeTypeEnum() != null)
                        .collect(Collectors.toList()));

        TransactionSummingDTO tsDTO = summingService.sumOfTransactionsInTimeRange(LocalDate.of(2020, Month.APRIL, 1),
                LocalDate.of(2020, Month.APRIL, 30));

        Assertions.assertTrue(tsDTO.getIncome().equals(BigDecimal.valueOf(50))
                && tsDTO.getOutcome().equals(BigDecimal.valueOf(40)));
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
