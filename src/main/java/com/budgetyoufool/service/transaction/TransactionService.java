package com.budgetyoufool.service.transaction;

import com.budgetyoufool.model.DTO.transaction.IncomeDTO;
import com.budgetyoufool.model.DTO.transaction.OutcomeDTO;

public interface TransactionService {
    IncomeDTO createIncome(IncomeDTO incomeDTO);
    OutcomeDTO createOutcome(OutcomeDTO dto);
//    void readTransaction();
//    void updateTransaction();
//    void deleteTransaction();

}
