package com.budgetyoufool.controller;

import com.budgetyoufool.exception.exceptions.DateException;
import com.budgetyoufool.exception.exceptions.DateMismatchException;
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
    public ResponseEntity<List<Transaction>> showListOfTransactionsByDay(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        if (date.isAfter(LocalDate.now())) {
            throw new DateException();
        } else {
            return ResponseEntity.ok(groupingService.getTransactionsListByDate(date));
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByMonth(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(groupingService.getTransactionsListByMonth(date));
    }

    @GetMapping("/inTimeRange")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByTimeRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        if (start.isAfter(LocalDate.now()) || end.isAfter(LocalDate.now())) {
            throw new DateException();
        } else if (start.isAfter(end)) {
            throw new DateMismatchException();
        } else {

            return ResponseEntity.ok(groupingService.getTransactionsListByTimeRange(start, end));
        }
    }
}
