package com.budgetyoufool.repository;

import com.budgetyoufool.model.transaction.income.Income;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepo extends MongoRepository<Income, String> {
}
