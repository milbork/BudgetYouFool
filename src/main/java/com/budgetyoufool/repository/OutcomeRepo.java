package com.budgetyoufool.repository;

import com.budgetyoufool.model.transaction.outcome.Outcome;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutcomeRepo extends MongoRepository<Outcome, String> {

}
