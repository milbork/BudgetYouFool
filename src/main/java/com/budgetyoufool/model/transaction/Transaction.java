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
@RequiredArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private String description;
    @NonNull
    private LocalDate date;

    private OutcomeTypeEnum outcomeTypeEnum;

    private IncomeTypeEnum incomeTypeEnum;
}
