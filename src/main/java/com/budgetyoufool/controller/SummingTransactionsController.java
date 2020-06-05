package com.budgetyoufool.controller;

import com.budgetyoufool.exception.exceptions.DateException;
import com.budgetyoufool.exception.exceptions.DateMismatchException;
import com.budgetyoufool.model.DTO.TransactionSummingDTO;
import com.budgetyoufool.service.summingValues.SummingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/transactions/sum")
public class SummingTransactionsController {

    private final SummingService summingService;

    public SummingTransactionsController(SummingService summingService) {
        this.summingService = summingService;
    }

    @GetMapping(value = "/daily")
    public ResponseEntity<EntityModel<TransactionSummingDTO>> showSumOfDailyTransactions(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (date.isAfter(LocalDate.now())) {
            throw new DateException();
        } else {

            TransactionSummingDTO summingDTO = summingService.sumDailyTransactions(date);
            Link link = linkTo(methodOn(SummingTransactionsController.class)
                    .showSumOfDailyTransactions(date))
                    .withSelfRel();

            EntityModel<TransactionSummingDTO> entityModel = new EntityModel<>(summingDTO, link);


            return ResponseEntity.ok(entityModel);
        }
    }

    @GetMapping(value = "/monthly")
    public ResponseEntity<EntityModel<TransactionSummingDTO>> showSumOfMonthlyTransactions(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        TransactionSummingDTO summingDTO = summingService.sumOfMonthlyTransactions(date);
        Link link = linkTo(methodOn(SummingTransactionsController.class)
                .showSumOfMonthlyTransactions(date))
                .withSelfRel();
        EntityModel<TransactionSummingDTO> entityModel = new EntityModel<>(summingDTO, link);

        return ResponseEntity.ok(entityModel);
    }

    @GetMapping(value = "/range")
    public ResponseEntity<EntityModel<TransactionSummingDTO>> showSumOfTransactionsInTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        if (start.isAfter(LocalDate.now()) || end.isAfter(LocalDate.now())) {
            throw new DateException();
        } else if (start.isAfter(end)) {
            throw new DateMismatchException();
        } else {

            TransactionSummingDTO summingDTO = summingService.sumOfTransactionsInTimeRange(start, end);
            Link link = linkTo(methodOn(SummingTransactionsController.class)
                    .showSumOfTransactionsInTimeRange(start, end))
                    .withSelfRel();
            EntityModel<TransactionSummingDTO> entityModel = new EntityModel<>(summingDTO, link);

            return ResponseEntity.ok(entityModel);
        }
    }
}
