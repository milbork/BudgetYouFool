package com.budgetyoufool.controller;

import com.budgetyoufool.exception.exceptions.OperationFailedException;
import com.budgetyoufool.exception.exceptions.TransactionTypeException;
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

@Controller
@RequestMapping("/")
public class TransactionController {

    private final String NAME = "TRANSACTION_CONTROLLER";
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<String> addTransaction() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("responded", "MyController");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body("Add new transaction");
    }

    @PostMapping(path = "/transactions")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {

        if (transactionDTO.getIncomeTypeEnum() != null && transactionDTO.getOutcomeTypeEnum() == null
                || transactionDTO.getIncomeTypeEnum() == null && transactionDTO.getOutcomeTypeEnum() != null) {

            TransactionDTO transfer = transactionService.createTransaction(transactionDTO);

            try {
                URI uri = new URI(String.format("/transactions/%d", transfer.getId()));

                return ResponseEntity
                        .created(uri)
                        .body(transfer);

            } catch (URISyntaxException ex) {
                throw new URIResponseException(ex.toString());
            }

        } else {
            throw new TransactionTypeException();
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
