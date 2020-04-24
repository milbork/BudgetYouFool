package com.budgetyoufool.model.DTO.transaction;

import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.model.transaction.outcome.OutcomeTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class OutcomeDTO extends Transaction {
    @NonNull
    private OutcomeTypeEnum outcomeTypeEnum;

    public OutcomeDTO(@NonNull BigDecimal amount, @NonNull String description, @NonNull LocalDate date, OutcomeTypeEnum outcomeTypeEnum) {
        super(amount, description, date);
        this.outcomeTypeEnum = outcomeTypeEnum;
    }
}
