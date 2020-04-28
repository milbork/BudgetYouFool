package com.budgetyoufool.controller;

import com.budgetyoufool.model.DTO.TimeRangeDTO;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.service.timeRange.TimeRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DataDisplayController {

    private final TimeRangeService timeRangeService;

    @Autowired
    public DataDisplayController(TimeRangeService timeRangeService) {
        this.timeRangeService = timeRangeService;
    }

    @PostMapping("/show/date")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByDay(@RequestBody LocalDate date) {

        List<Transaction> transactions = timeRangeService.getTransactionsListByDate(date);
        System.out.println(date);
        System.out.println(transactions);

        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/show/timeRange")
    public ResponseEntity<List<Transaction>> showListOfTransactionsByTimeRange(@RequestBody TimeRangeDTO timeRangeDTO) {
        System.out.println(timeRangeDTO.getStart() +"\n" + timeRangeDTO.getEnd());
        List<Transaction> transactions = timeRangeService.getTransactionsListByTimeRange(timeRangeDTO.getStart(), timeRangeDTO.getEnd());

        System.out.println(transactions);

        return ResponseEntity.ok(transactions);
    }
}
