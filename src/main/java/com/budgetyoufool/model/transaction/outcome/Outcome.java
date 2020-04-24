package com.budgetyoufool.model.transaction.outcome;

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
public class Outcome extends Transaction {

    @NonNull
    private OutcomeTypeEnum outcomeTypeEnum;

    public Outcome(@NonNull BigDecimal amount, @NonNull String description, @NonNull LocalDate date, OutcomeTypeEnum outcomeTypeEnum) {
        super(amount, description, date);
        this.outcomeTypeEnum = outcomeTypeEnum;
    }
}
