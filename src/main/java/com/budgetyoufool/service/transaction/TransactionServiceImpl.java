package com.budgetyoufool.service.transaction;

import com.budgetyoufool.model.DTO.transaction.IncomeDTO;
import com.budgetyoufool.model.DTO.transaction.OutcomeDTO;
import com.budgetyoufool.model.transaction.income.Income;
import com.budgetyoufool.model.transaction.outcome.Outcome;
import com.budgetyoufool.repository.IncomeRepo;
import com.budgetyoufool.repository.OutcomeRepo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final OutcomeRepo outcomeRepo;
    private final IncomeRepo incomeRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public TransactionServiceImpl(OutcomeRepo outcomeRepo, IncomeRepo incomeRepo) {
        this.outcomeRepo = outcomeRepo;
        this.incomeRepo = incomeRepo;
    }

    @Override
    public IncomeDTO createIncome(IncomeDTO incomeDTO) {
        System.out.println(incomeDTO.getId());
        Income income = modelMapper.map(incomeDTO, Income.class);
        Income save = incomeRepo.save(income);

        return modelMapper.map(save, IncomeDTO.class);
    }
    @Override
    public OutcomeDTO createOutcome(OutcomeDTO outcomeDTO) {

        Outcome outcome = modelMapper.map(outcomeDTO, Outcome.class);
        Outcome save = outcomeRepo.save(outcome);

        return modelMapper.map(save, OutcomeDTO.class);
    }

    public void readTransaction() {

    }

    public void updateTransaction() {

    }

    public void deleteTransaction() {

    }
}
