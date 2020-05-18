package com.budgetyoufool.controller;

import com.budgetyoufool.BYFApplication;
import com.budgetyoufool.model.DTO.TransactionDTO;
import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.service.transaction.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(classes = BYFApplication.class)
@AutoConfigureMockMvc
class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionService service;

    @Test
    void addTransaction() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Add new transaction")
                );
    }

    @Test
    void testAddTransaction() {
    }

    @Test
    void readTransaction() throws Exception {

        TransactionDTO transactionDTO = new TransactionDTO(1L, BigDecimal.valueOf(25), "snack",
                LocalDate.of(2020, Month.APRIL, 13), null, IncomeTypeEnum.SALARY);

        when(service.showTransaction(1L)).thenReturn(transactionDTO);

        mvc.perform(MockMvcRequestBuilders
                .get("/transactions/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(25))
                .andExpect(MockMvcResultMatchers.jsonPath("$.incomeTypeEnum").value("SALARY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date").value("2020-04-13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("snack")
                );
    }

    @Test
    void updateTransaction() {
    }

    @Test
    void deleteTransaction() {
    }
}
