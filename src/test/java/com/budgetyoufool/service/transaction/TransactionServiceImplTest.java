package com.budgetyoufool.service.transaction;

import com.budgetyoufool.exception.exceptions.NoSuchTransactionException;
import com.budgetyoufool.model.DTO.TransactionDTO;
import com.budgetyoufool.repository.TransactionRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class TransactionServiceImplTest {

    @MockBean
    private TransactionRepo repo;
    private TransactionServiceImpl transactionService;
    private ModelMapper modelMapper;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        transactionService = new TransactionServiceImpl(repo, modelMapper);
    }

    @Test
    void shouldThrowExceptionIfCantFindTransaction() {

        when(repo.findById(100L)).thenReturn(Optional.empty());

        Assertions.assertAll(
                () -> assertThrows(NoSuchTransactionException.class, () -> transactionService.showTransaction(100L)),
                () -> assertThrows(NoSuchTransactionException.class, () -> transactionService.deleteTransaction(100L)),
                () -> assertThrows(NoSuchTransactionException.class, () -> transactionService.updateTransaction(new TransactionDTO()))
        );
    }
}
