package com.budgetyoufool.controller;

import com.budgetyoufool.BYFApplication;
import com.budgetyoufool.model.DTO.TransactionDTO;
import com.budgetyoufool.service.transaction.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static com.budgetyoufool.model.transaction.IncomeTypeEnum.SALARY;
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
    void addTransactionTest_GET() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Add new transaction")
                );
    }

    @Test
    void addTransactionTest_POST() throws Exception {

        TransactionDTO transactionDTO = new TransactionDTO(1L, BigDecimal.valueOf(25), "snack",
                LocalDate.of(2020, Month.APRIL, 13), null, SALARY);

        when(service.createTransaction(transactionDTO)).thenReturn(transactionDTO);

        mvc.perform(MockMvcRequestBuilders
                .post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(transactionDTO))
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(25)
                );
    }

    @Test
    void readTransactionTest_GET() throws Exception {

        TransactionDTO transactionDTO = new TransactionDTO(1L, BigDecimal.valueOf(25), "snack",
                LocalDate.of(2020, Month.APRIL, 13), null, SALARY);

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
    void updateTransactionTest_PUT() throws Exception {

        TransactionDTO transactionDTO = new TransactionDTO(null, BigDecimal.valueOf(25), "snack",
                LocalDate.of(2020, Month.APRIL, 13), null, SALARY);

        mvc.perform(MockMvcRequestBuilders
                .put("/transactions/{id}", 1)
                .content(asJsonString(transactionDTO))
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Transaction successfully updated!")
                );
    }

    @Test
    void deleteTransactionTest_DELETE() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .delete("/transactions/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction removed")
                );
    }

    private String asJsonString(TransactionDTO transactionDTO) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(transactionDTO);
    }

}
