package com.budgetyoufool.model.transaction;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Document
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    private String id;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private String description;
    @NonNull
    private Date date;


}
