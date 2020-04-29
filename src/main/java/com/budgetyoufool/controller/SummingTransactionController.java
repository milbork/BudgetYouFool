package com.budgetyoufool.controller;


import com.budgetyoufool.service.summingValues.SummingService;
import org.springframework.stereotype.Controller;

@Controller
public class SummingTransactionController {

    SummingService summingService;

    public SummingTransactionController(SummingService summingService) {
        this.summingService = summingService;
    }


}
