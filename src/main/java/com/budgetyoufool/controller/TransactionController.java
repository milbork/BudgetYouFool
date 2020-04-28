package com.budgetyoufool.controller;

import com.budgetyoufool.model.DTO.TransactionDTO;
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

    @GetMapping(value = "/addTransaction/income")
    public ResponseEntity<String> addIncome() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("responded", "MyController");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body("Set new income");
    }

    @PostMapping(value = "/addTransaction/income", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> addIncome(@RequestBody TransactionDTO transactionDTO) throws URISyntaxException {

        TransactionDTO transfer = transactionService.createTransaction(transactionDTO);

        return ResponseEntity
                .created(new URI(String.format("/addTransaction/income/%d", transfer.getId())))
                .body(transfer);
    }

    @GetMapping(value = "/addTransaction/outcome")
    public ResponseEntity<String> addOutcome() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("responded", "MyController");
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body("Set new outcome");
    }

    @PostMapping(value = "/addTransaction/outcome", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDTO> addOutcome(@RequestBody TransactionDTO transaction) throws URISyntaxException {

        TransactionDTO transfer = transactionService.createTransaction(transaction);

        return ResponseEntity
                .created(new URI(String.format("/addTransaction/outcome/%d", transfer.getId())))
                .body(transfer);
    }
}
