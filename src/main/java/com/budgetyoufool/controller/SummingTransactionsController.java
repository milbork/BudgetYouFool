package com.budgetyoufool.controller;

import com.budgetyoufool.model.DTO.TimeRangeDTO;
import com.budgetyoufool.service.summingValues.SummingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller("/sum")
public class SummingTransactionsController {

    private final SummingService summingService;

    public SummingTransactionsController(SummingService summingService) {
        this.summingService = summingService;
    }

    @PostMapping("/daily")
    public ResponseEntity<List<BigDecimal>> showSumOfDailyTransactions(@RequestBody LocalDate date) {

        return ResponseEntity.ok(summingService.sumDailyTransactions(date));
    }

    @PostMapping("/monthly")
    public ResponseEntity<List<BigDecimal>> showSumOfMonthlyTransactions(@RequestBody LocalDate date) {

        return ResponseEntity.ok(summingService.sumOfMonthlyTransactions(date));
    }

    @PostMapping("/range")
    public ResponseEntity<List<BigDecimal>> showSumOfTransactionsInTimeRange(@RequestBody TimeRangeDTO date) {

        return ResponseEntity.ok(summingService.sumOfTransactionsInTimeRange(date));
    }
}
