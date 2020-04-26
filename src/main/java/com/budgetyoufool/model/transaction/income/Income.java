package com.budgetyoufool.model.transaction.income;

import com.budgetyoufool.model.transaction.Transaction;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Income extends Transaction {

    @NonNull
    private IncomeTypeEnum incomeTypeEnum;

    public Income(@NonNull BigDecimal amount, @NonNull String description, @NonNull Date date, IncomeTypeEnum incomeTypeEnum) {
        super(amount, description, date);
        this.incomeTypeEnum = incomeTypeEnum;
    }

}
