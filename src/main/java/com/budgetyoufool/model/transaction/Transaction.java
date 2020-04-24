package com.budgetyoufool.model.transaction;


import lombok.*;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private String description;
    @NonNull
    private LocalDate date;


}
