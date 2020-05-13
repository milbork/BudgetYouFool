package com.budgetyoufool.repository;

import com.budgetyoufool.BYFApplication;
import com.budgetyoufool.model.transaction.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(classes = BYFApplication.class)
@AutoConfigureTestEntityManager
@Transactional
class TransactionRepoTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TransactionRepo transactionRepo;

    @Test
    void shouldPassIfReturnsCorrectTransactionsByDate() {

        Transaction testTransaction = new Transaction(BigDecimal.valueOf(5), "snack", LocalDate.of(2020, Month.APRIL, 15));
        entityManager.persistAndFlush(testTransaction);
        Transaction testTransaction1 = new Transaction(BigDecimal.valueOf(5), "snack", LocalDate.now());
        entityManager.persistAndFlush(testTransaction1);
        Transaction testTransaction2 = new Transaction(BigDecimal.valueOf(5), "snack", LocalDate.now());
        entityManager.persistAndFlush(testTransaction2);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(testTransaction);

        List<Transaction> found = this.transactionRepo.findAllByDateEquals(LocalDate.of(2020, Month.APRIL, 15));
        assertThat(found).isEqualTo(transactions);
    }

    @Test
    void shouldPassIfReturnsCorrectTransactionsInTimeRange() {

        Transaction testTransaction = new Transaction(BigDecimal.valueOf(5), "snack", LocalDate.of(2020, Month.MARCH, 17));
        entityManager.persistAndFlush(testTransaction);
        Transaction testTransaction1 = new Transaction(BigDecimal.valueOf(15), "snack", LocalDate.of(2020, Month.APRIL, 10));
        entityManager.persistAndFlush(testTransaction1);
        Transaction testTransaction2 = new Transaction(BigDecimal.valueOf(25), "snack", LocalDate.of(2020, Month.APRIL, 15));
        entityManager.persistAndFlush(testTransaction2);
        Transaction testTransaction3 = new Transaction(BigDecimal.valueOf(35), "snack", LocalDate.of(2020, Month.APRIL, 30));
        entityManager.persistAndFlush(testTransaction3);
        Transaction testTransaction4 = new Transaction(BigDecimal.valueOf(45), "snack", LocalDate.of(2020, Month.MAY, 15));
        entityManager.persistAndFlush(testTransaction4);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(testTransaction1);
        transactions.add(testTransaction2);
        transactions.add(testTransaction3);

        List<Transaction> found = this.transactionRepo.findAllByDateBetween(LocalDate.of(2020, Month.MARCH, 31),
                                                                            LocalDate.of(2020, Month.MAY, 1));
        assertThat(found).isEqualTo(transactions);
    }
}
