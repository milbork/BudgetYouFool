package com.budgetyoufool.controller;

import com.budgetyoufool.model.DTO.TransactionSummingDTO;
import com.budgetyoufool.service.summingValues.SummingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping(value = "/sum")
public class SummingTransactionsController {

    private final SummingService summingService;

    public SummingTransactionsController(SummingService summingService) {
        this.summingService = summingService;
    }

    @GetMapping(value = "/daily")
    public ResponseEntity<TransactionSummingDTO> showSumOfDailyTransactions(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(summingService.sumDailyTransactions(date));
    }

    @GetMapping(value = "/monthly")
    public ResponseEntity<TransactionSummingDTO> showSumOfMonthlyTransactions(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(summingService.sumOfMonthlyTransactions(date));
    }

    @GetMapping(value = "/range")
    public ResponseEntity<TransactionSummingDTO> showSumOfTransactionsInTimeRange(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {


        return ResponseEntity.ok(summingService.sumOfTransactionsInTimeRange(start, end));
    }
}
