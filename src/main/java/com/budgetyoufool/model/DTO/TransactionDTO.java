package com.budgetyoufool.model.DTO;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TransactionDTO {

    private Long id;

    private IncomeTypeEnum incomeTypeEnum;

    private OutcomeTypeEnum outcomeTypeEnum;
    @NotNull(message = "Transaction must contains amount!")
    private BigDecimal amount;
    @NotBlank(message = "Transaction must contains description")
    private String description;
    @NotNull(message = "Transaction must contains date")
    private LocalDate date;
}
