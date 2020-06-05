package com.budgetyoufool.controller;

import com.budgetyoufool.exception.exceptions.DateException;
import com.budgetyoufool.exception.exceptions.OperationFailedException;
import com.budgetyoufool.exception.exceptions.TransactionTypeException;
import com.budgetyoufool.exception.exceptions.URIResponseException;
import com.budgetyoufool.model.DTO.TransactionDTO;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.service.transaction.TransactionService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/")
public class TransactionController {

    private final String NAME = "TRANSACTION_CONTROLLER";
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(path = "/transactions")
    public ResponseEntity<EntityModel<String>> addTransaction() {

        Link link = linkTo(methodOn(TransactionController.class).addTransaction()).withSelfRel();
        EntityModel<String> entityModel = new EntityModel<>("Add new transaction", link);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityModel);
    }

    @PostMapping(path = "/transactions")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody @Valid TransactionDTO transactionDTO) {

        if (transactionDTO.getDate().isAfter(LocalDate.now())) {
            throw new DateException();

        } else if (transactionDTO.getIncomeTypeEnum() != null && transactionDTO.getOutcomeTypeEnum() == null
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
    public ResponseEntity<EntityModel<TransactionDTO>> readTransaction(@PathVariable @NotNull @Valid Long id) {

        Optional<TransactionDTO> transactionDTO = Optional.ofNullable(transactionService.showTransaction(id));

        if (transactionDTO.isPresent()) {
            Link link = linkTo(TransactionController.class).slash("transactions").slash(id).withSelfRel();
            EntityModel<TransactionDTO> entityModel = new EntityModel<>(transactionDTO.get(), link);

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(entityModel);
        } else {
            throw new OperationFailedException(NAME);
        }
    }

    @PutMapping(path = "/transactions/{id}")
    public ResponseEntity<EntityModel<String>> updateTransaction(@PathVariable @NotNull @Valid Long id,
                                                                 @RequestBody TransactionDTO transactionDTO) {

        if (transactionDTO.getDate().isAfter(LocalDate.now())) {
            throw new DateException();
        } else if (transactionDTO.getIncomeTypeEnum() != null && transactionDTO.getOutcomeTypeEnum() == null
                || transactionDTO.getIncomeTypeEnum() == null && transactionDTO.getOutcomeTypeEnum() != null) {

            transactionDTO.setId(id);
            transactionService.updateTransaction(transactionDTO);

            Link link = linkTo(TransactionController.class)
                    .slash("transactions")
                    .slash(transactionDTO.getId())
                    .withSelfRel();

            EntityModel<String> entityModel = new EntityModel<>("Transaction successfully updated!", link);

            return ResponseEntity
                    .ok()
                    .body(entityModel);
        } else {
            throw new TransactionTypeException();
        }
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
