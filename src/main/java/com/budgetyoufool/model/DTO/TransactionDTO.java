package com.budgetyoufool.model.DTO;

import com.budgetyoufool.model.transaction.IncomeTypeEnum;
import com.budgetyoufool.model.transaction.OutcomeTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

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
    @PastOrPresent
    private LocalDate date;

    public TransactionDTO(BigDecimal amount, String description, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
    public TransactionDTO(Long id ,BigDecimal amount, String description, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
    public TransactionDTO(Long id, BigDecimal amount, String description, LocalDate date, OutcomeTypeEnum outcomeTypeEnum, IncomeTypeEnum incomeTypeEnum) {

        this.amount = amount;
        this.description = description;
        this.date = date;
        this.outcomeTypeEnum = outcomeTypeEnum;
        this.incomeTypeEnum = incomeTypeEnum;
    }
}
