package com.budgetyoufool.model.transaction.income;

import com.budgetyoufool.model.transaction.Transaction;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity

public class Income extends Transaction {

    private IncomeType incomeType;

    public Income() {
    }

    public Income(long id, BigDecimal amount, LocalDate date, String description, IncomeType incomeType) {
        super(id, amount, date, description);
        this.incomeType = incomeType;
    }
}
