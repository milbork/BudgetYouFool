package com.budgetyoufool.controller;

import com.budgetyoufool.model.DTO.transaction.IncomeDTO;
import com.budgetyoufool.model.transaction.income.Income;
import com.budgetyoufool.service.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@Controller
@RequestMapping("/")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }



    @GetMapping(value = "/addTransaction/income", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> newTransaction() {
        Income income = new Income();
        HttpHeaders headers = new HttpHeaders();
        headers.add("responded", "MyController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body("Set new income");
    }

    @PostMapping(value = "/addTransaction/income", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomeDTO> newTransaction(@RequestBody IncomeDTO income) throws URISyntaxException {

        IncomeDTO save = transactionService.createIncome(income);

        return ResponseEntity
                .created(new URI(String.format("/addTransaction/income/%d", save.getId())))
                .body(save);
    }
}
