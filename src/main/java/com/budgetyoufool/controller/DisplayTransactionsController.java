package com.budgetyoufool.controller;

import com.budgetyoufool.exception.exceptions.DateException;
import com.budgetyoufool.exception.exceptions.DateMismatchException;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.service.grupingTransactions.GroupingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/transactions")
public class DisplayTransactionsController {

    private final GroupingService groupingService;

    public DisplayTransactionsController(GroupingService groupingService) {
        this.groupingService = groupingService;
    }

    @GetMapping("/daily")
    public ResponseEntity<CollectionModel<Transaction>> showListOfTransactionsByDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (date.isAfter(LocalDate.now())) {
            throw new DateException();
        } else {
            List<Transaction> transactionList = groupingService.getTransactionsListByDate(date);

            addLinksToTransactions(transactionList);

            Link link = linkTo(methodOn(DisplayTransactionsController.class)
                    .showListOfTransactionsByDay(date))
                    .withSelfRel();

            CollectionModel<Transaction> transactionCollectionModel = new CollectionModel<>(transactionList, link);

            return ResponseEntity.ok().body(transactionCollectionModel);
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<CollectionModel<Transaction>> showListOfTransactionsByMonth(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Transaction> transactionList = groupingService.getTransactionsListByMonth(date);

        addLinksToTransactions(transactionList);

        Link link = linkTo(methodOn(DisplayTransactionsController.class)
                .showListOfTransactionsByMonth(date))
                .withSelfRel();

        CollectionModel<Transaction> collectionModel = new CollectionModel<>(transactionList, link);

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/inTimeRange")
    public ResponseEntity<CollectionModel<Transaction>> showListOfTransactionsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        if (start.isAfter(LocalDate.now()) || end.isAfter(LocalDate.now())) {
            throw new DateException();
        } else if (start.isAfter(end)) {
            throw new DateMismatchException();
        } else {

            List<Transaction> transactionList = groupingService.getTransactionsListByTimeRange(start, end);
            addLinksToTransactions(transactionList);

            Link link = linkTo(methodOn(DisplayTransactionsController.class)
                    .showListOfTransactionsByTimeRange(start, end))
                    .withSelfRel();

            CollectionModel<Transaction> collectionModel = new CollectionModel<>(transactionList, link);

            return ResponseEntity.ok(collectionModel);
        }

    }
    private static void addLinksToTransactions(List<Transaction> list){

        list.forEach(t -> t.add(linkTo(TransactionController.class)
                .slash("transactions")
                .slash(t.getId())
                .withSelfRel())
        );
    }
}
