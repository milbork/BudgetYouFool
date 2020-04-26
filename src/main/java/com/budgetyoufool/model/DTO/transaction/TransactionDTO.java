package com.budgetyoufool.model.DTO.transaction;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
public class TransactionDTO {


    private String id;

    @NonNull
    private BigDecimal amount;
    @NonNull
    private String description;
    @NonNull
    private Date date;

    public TransactionDTO(BigDecimal amount, String description, Date date){
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
}
