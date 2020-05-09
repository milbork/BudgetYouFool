package com.budgetyoufool.controller;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.service.grupingTransactions.GroupingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/transactions")
public class DisplayTransactionsController {

    private final GroupingService groupingService;

    public DisplayTransactionsController(GroupingService groupingService) {
        this.groupingService = groupingService;
    }

    @GetMapping("/daily")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Transaction> transactions = groupingService.getTransactionsListByDate(date);

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByMonth(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Transaction> transactions = groupingService.getTransactionsListByMonth(date);

        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/inTimeRange")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByTimeRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start,
                                                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end) {

        List<Transaction> transactions = groupingService.getTransactionsListByTimeRange(start, end);

        return ResponseEntity.ok(transactions);
    }
}
