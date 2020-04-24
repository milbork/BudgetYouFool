package com.budgetyoufool.model.DTO.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TransactionDTO {


    private long id;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private String description;
    @NonNull
    private LocalDate date;
}
