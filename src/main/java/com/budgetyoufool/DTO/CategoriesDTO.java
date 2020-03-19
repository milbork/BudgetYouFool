package com.budgetyoufool.DTO;

import com.budgetyoufool.entitis.Transaction;

import java.util.List;

public class CategoriesDTO {
    private Long id;
    private String name;
    private List<Transaction> transactions;

    public CategoriesDTO() {
    }

    public CategoriesDTO(Long id, String name, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

