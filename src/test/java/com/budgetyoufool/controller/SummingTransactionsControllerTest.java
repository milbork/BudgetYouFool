package com.budgetyoufool.controller;

import com.budgetyoufool.BYFApplication;
import com.budgetyoufool.model.DTO.TransactionSummingDTO;
import com.budgetyoufool.service.summingValues.SummingService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest(classes = BYFApplication.class)
@AutoConfigureMockMvc
class SummingTransactionsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    SummingService service;

    @Test
    void showSumOfDailyTransactionsTest_GET() throws Exception {

        TransactionSummingDTO dto = new TransactionSummingDTO(BigDecimal.valueOf(100), BigDecimal.valueOf(50));
        when(service.sumDailyTransactions(LocalDate.of(2020, Month.APRIL, 13)))
                .thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/sum/daily")
                .param("date", "2020-04-13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.income").value(100))
                .andExpect(jsonPath("$.outcome").value(50));

    }

    @Test
    void showSumOfMonthlyTransactionsTest_GET() throws Exception {
        TransactionSummingDTO dto = new TransactionSummingDTO(BigDecimal.valueOf(100), BigDecimal.valueOf(50));
        when(service.sumOfMonthlyTransactions(LocalDate.of(2020, Month.APRIL, 13)))
                .thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/sum/monthly")
                .param("date", "2020-04-13"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.income").value(100))
                .andExpect(jsonPath("$.outcome").value(50));
    }

    @Test
    void showSumOfTransactionsInTimeRangeTest_GET() throws Exception {
        TransactionSummingDTO dto = new TransactionSummingDTO(BigDecimal.valueOf(100), BigDecimal.valueOf(50));
        when(service.sumOfTransactionsInTimeRange(LocalDate.of(2020, 4, 1), LocalDate.of(2020, 4, 30)))
                .thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/sum/range")
                .param("start", "2020-04-01")
                .param("end", "2020-04-30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.income").value(100))
                .andExpect(jsonPath("$.outcome").value(50));
    }
}
