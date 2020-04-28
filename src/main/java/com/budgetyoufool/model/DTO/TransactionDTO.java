package com.budgetyoufool.model.DTO;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class TransactionDTO {

    private Long id;

    private IncomeTypeEnum incomeTypeEnum;

    private OutcomeTypeEnum outcomeTypeEnum;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private String description;
    @NonNull
    private LocalDate date;
}
