package com.budgetyoufool.model.transaction.income;

import com.budgetyoufool.model.transaction.Transaction;
import lombok.*;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Income extends Transaction {

    @NonNull
    private IncomeTypeEnum incomeTypeEnum;

    public Income(@NonNull BigDecimal amount, @NonNull String description, @NonNull LocalDate date, IncomeTypeEnum incomeTypeEnum) {
        super(amount, description, date);
        this.incomeTypeEnum = incomeTypeEnum;
    }

}
