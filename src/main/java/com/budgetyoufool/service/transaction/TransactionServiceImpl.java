package com.budgetyoufool.service.transaction;

import com.budgetyoufool.model.DTO.transaction.IncomeDTO;
import com.budgetyoufool.model.DTO.transaction.OutcomeDTO;
import com.budgetyoufool.model.transaction.Transaction;
import com.budgetyoufool.model.transaction.income.Income;
import com.budgetyoufool.repository.TransactionRepo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public IncomeDTO createIncome(IncomeDTO incomeDTO) {

        Transaction income = modelMapper.map(incomeDTO, Income.class);
        System.out.println(incomeDTO.getIncomeTypeEnum());
        Transaction save = transactionRepo.save(income);

        return modelMapper.map(save, IncomeDTO.class);
    }

    public void createOutcome(OutcomeDTO outcomeDTO) {


    }

    public void readTransaction() {

    }

    public void updateTransaction() {

    }

    public void deleteTransaction() {

    }
}
