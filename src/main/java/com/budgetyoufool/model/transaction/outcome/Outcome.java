package com.budgetyoufool.model.transaction.outcome;

import com.budgetyoufool.model.transaction.Transaction;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Outcome extends Transaction {

    @NonNull
    private OutcomeTypeEnum outcomeTypeEnum;

    public Outcome(@NonNull BigDecimal amount, @NonNull String description, @NonNull
            Date date, OutcomeTypeEnum outcomeTypeEnum) {
        super(amount, description, date);
        this.outcomeTypeEnum = outcomeTypeEnum;
    }
}
