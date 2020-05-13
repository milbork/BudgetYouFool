package com.budgetyoufool.controller;

import com.budgetyoufool.exception.exceptions.OperationFailedException;
import com.budgetyoufool.exception.exceptions.URIResponseException;
import com.budgetyoufool.model.DTO.TransactionDTO;
import com.budgetyoufool.service.transaction.TransactionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class TransactionController {

    private final String NAME = "TRANSACTION_CONTROLLER";
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/transactions/income")
    public ResponseEntity<String> addIncome() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("responded", "MyController");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body("Set new income");
    }

    @PostMapping(path = "/transactions/income")
    public ResponseEntity<TransactionDTO> addIncome(@RequestBody @Valid TransactionDTO transactionDTO) {

        TransactionDTO transfer = transactionService.createTransaction(transactionDTO);

        try {
            URI uri = new URI(String.format("/transactions/income/%d", transfer.getId()));

            return ResponseEntity
                    .created(uri)
                    .body(transfer);

        } catch (URISyntaxException ex) {
            throw new URIResponseException(ex.toString());
        }
    }

    @GetMapping(path = "/transactions/outcome")
    public ResponseEntity<String> addOutcome() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("responded", "MyController");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body("Set new outcome");
    }

    @PostMapping(path = "/transactions/outcome")
    public ResponseEntity<TransactionDTO> addOutcome(@RequestBody @Valid TransactionDTO transaction) {

        TransactionDTO transfer = transactionService.createTransaction(transaction);

        try {
            URI uri = new URI(String.format("/transactions/%d", transfer.getId()));

            return ResponseEntity
                    .created(uri)
                    .body(transfer);

        } catch (URISyntaxException ex) {
            throw new URIResponseException(ex.toString());
        }
    }

    @GetMapping(path = "/transactions/{id}")
    public ResponseEntity<TransactionDTO> readTransaction(@PathVariable @NotNull @Valid Long id) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(transactionService.showTransaction(id));
    }

    @PutMapping(path = "/transactions/{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable @NotNull @Valid Long id,
                                                    @RequestBody TransactionDTO transactionDTO) {

        transactionDTO.setId(id);
        transactionService.updateTransaction(transactionDTO);

        return ResponseEntity
                .ok()
                .body("Transaction successfully updated!");
    }

    @DeleteMapping(path = "/transactions/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable @NotNull @Valid Long id) {

        if (!transactionService.deleteTransaction(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Transaction removed");
        } else {
            throw new OperationFailedException(NAME);
        }
    }
}
