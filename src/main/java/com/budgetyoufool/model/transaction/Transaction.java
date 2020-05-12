package com.budgetyoufool.model.transaction;

import lombok.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private BigDecimal amount;

    private String description;

    private LocalDate date;

    private OutcomeTypeEnum outcomeTypeEnum;

    private IncomeTypeEnum incomeTypeEnum;
}
