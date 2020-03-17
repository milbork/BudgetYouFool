package com.budgetyoufool.entitis;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {

    private Long id;
    private String name;
    private LocalDate date;
    private Categories category;
    private BigDecimal amount;

    public Transaction(String name) {
        this.name = name;
    }

    public Transaction(Long id, String name, LocalDate date, Categories category, BigDecimal amount) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
