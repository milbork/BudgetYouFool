package com.budgetyoufool.controller;

import com.budgetyoufool.model.DTO.TimeRangeDTO;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.service.grupingTransactions.GroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TransactionsDisplayController {

    private final GroupingService groupingService;

    @Autowired
    public TransactionsDisplayController(GroupingService groupingService) {
        this.groupingService = groupingService;
    }

    @PostMapping("/show/date")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByDay(@RequestBody LocalDate date) {

        List<Transaction> transactions = groupingService.getTransactionsListByDate(date);
        System.out.println(date);
        System.out.println(transactions);

        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/show/byMonth")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByMonth(@RequestBody LocalDate date) {

        List<Transaction> transactions = groupingService.getTransactionsListByMonth(date);

        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/show/timeRange")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByTimeRange(@RequestBody TimeRangeDTO timeRangeDTO) {

        List<Transaction> transactions = groupingService.getTransactionsListByTimeRange(timeRangeDTO.getStart(), timeRangeDTO.getEnd());

        return ResponseEntity.ok(transactions);
    }
}
