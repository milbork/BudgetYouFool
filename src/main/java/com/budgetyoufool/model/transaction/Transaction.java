package com.budgetyoufool.model.transaction;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
public class Transaction extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private BigDecimal amount;

    private String description;

    private LocalDate date;

    private OutcomeTypeEnum outcomeTypeEnum;

    private IncomeTypeEnum incomeTypeEnum;

    public Transaction(BigDecimal amount, String description, LocalDate date, OutcomeTypeEnum outcomeTypeEnum, IncomeTypeEnum incomeTypeEnum) {

        this.amount = amount;
        this.description = description;
        this.date = date;
        this.outcomeTypeEnum = outcomeTypeEnum;
        this.incomeTypeEnum = incomeTypeEnum;
    }

    public Transaction(BigDecimal amount, String description, LocalDate date) {

        this.amount = amount;
        this.description = description;
        this.date = date;

    }
}
