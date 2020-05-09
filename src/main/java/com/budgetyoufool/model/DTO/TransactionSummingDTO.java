package com.budgetyoufool.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionSummingDTO {

    BigDecimal income;
    BigDecimal outcome;

}
