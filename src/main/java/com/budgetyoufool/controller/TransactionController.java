package com.budgetyoufool.controller;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.model.transaction.income.Income;
import com.budgetyoufool.model.transaction.income.IncomeType;
import com.budgetyoufool.model.transaction.outcome.Outcome;
import com.budgetyoufool.repository.TransactionRepo;
import io.swagger.models.auth.In;
import org.apache.tomcat.util.http.HeaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
public class TransactionController {

    private final TransactionRepo transactionRepo;

    @Autowired
    public TransactionController(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @GetMapping("/new")
    public ResponseEntity<Income> newTransaction() {
        Income income = new Income();
        HttpHeaders headers = new HttpHeaders();
        headers.add("responded", "MyController");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(headers).body(income);
    }
}
