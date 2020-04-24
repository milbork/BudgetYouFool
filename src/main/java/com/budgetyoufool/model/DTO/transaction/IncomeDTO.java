package com.budgetyoufool.model.DTO.transaction;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.model.transaction.income.IncomeTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class IncomeDTO extends Transaction {

    @NonNull
    private IncomeTypeEnum incomeTypeEnum;

    public IncomeDTO(@NonNull BigDecimal amount, @NonNull String description, @NonNull LocalDate date, IncomeTypeEnum incomeTypeEnum) {
        super(amount, description, date);
        this.incomeTypeEnum = incomeTypeEnum;
    }
}
