package com.budgetyoufool.controller;

import com.budgetyoufool.BYFApplication;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.service.grupingTransactions.GroupingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static com.budgetyoufool.model.transaction.IncomeTypeEnum.SALARY;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(classes = BYFApplication.class)
@AutoConfigureMockMvc
class DisplayTransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    GroupingService service;

    @Test
    void showListOfTransactionsByDayTest_GET() throws Exception {

        when(service.getTransactionsListByDate(LocalDate.of(2020, Month.APRIL, 13)))
                .thenReturn(transactionListInit());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/transactions/daily")
                .param("date", "2020-04-13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void showListOfTransactionsByMonthTest_GET() throws Exception {

        when(service.getTransactionsListByMonth(LocalDate.of(2020, Month.APRIL, 13)))
                .thenReturn(transactionListInit());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/transactions/monthly")
                .param("date", "2020-04-13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void showListOfTransactionsByTimeRangeTest_GET() throws Exception {

        when(service.getTransactionsListByTimeRange(LocalDate.of(2020, Month.APRIL, 13), LocalDate.of(2020, 5, 1)))
                .thenReturn(transactionListInit());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/transactions/inTimeRange")
                .param("start", "2020-04-13")
                .param("end", "2020-05-01"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    private List<Transaction> transactionListInit() {

        Transaction transaction1 = new Transaction(BigDecimal.valueOf(25), "snack",
                LocalDate.of(2020, Month.APRIL, 13), null, SALARY);
        Transaction transaction2 = new Transaction(BigDecimal.valueOf(50), "snack",
                LocalDate.of(2020, Month.APRIL, 13), null, SALARY);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        return transactions;
    }
}
