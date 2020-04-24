package com.budgetyoufool.model.transaction;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private BigDecimal amount;
    private String description;
    private LocalDate date;

    public Transaction() {
    }


    public Transaction(long id, BigDecimal amount, LocalDate date, String description) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }
}
