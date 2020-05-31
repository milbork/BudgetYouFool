package com.budgetyoufool.controller;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;
import com.budgetyoufool.service.statistics.StatisticsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/transactions")
public class StatisticController {

    private final StatisticsService service;

    StatisticController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<IncomeTypeEnum, BigDecimal>> showIncomesByType(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity
                .ok()
                .body(service.getIncomeStatsByDateAndType(start, end));

    }

    @GetMapping("/stats")
    public ResponseEntity<Map<OutcomeTypeEnum, BigDecimal>> showOutcomesByType(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity
                .ok()
                .body(service.getOutcomeStatsByDateAndType(start, end));

    }
}
