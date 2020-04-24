package com.budgetyoufool.model.transaction.outcome;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.model.transaction.income.IncomeType;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Outcome extends Transaction {

    private OutcomeType outcomeType;


    public Outcome() {
    }

    public Outcome(long id, BigDecimal amount, LocalDate date, String description, OutcomeType outcomeType) {
        super(id, amount, date, description);
        this.outcomeType = outcomeType;
    }

}
