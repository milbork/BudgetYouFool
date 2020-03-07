package com.budgetyoufool.entitis;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity

public class Transaction {
    private String description;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionLabel label;

    public Transaction() {
    }

    public Transaction(String description, BigDecimal amount, LocalDate date, TransactionLabel label) {
        if (BigDecimal.ZERO.compareTo(amount) > 0) {
            throw new IllegalArgumentException("Transaction value can't be negative");
        }
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TransactionLabel getLabel() {
        return label;
    }

    public void setLabel(TransactionLabel label) {
        this.label = label;
    }
}
