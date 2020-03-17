package com.budgetyoufool.services;

import com.budgetyoufool.entitis.Categories;
import com.budgetyoufool.interfaces.CategoriesInterface;
import com.budgetyoufool.repositorys.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServices implements CategoriesInterface {

    private final CategoriesRepo categoriesRepo;

    @Autowired
    public CategoriesServices(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }


    @Override
    public void createCategory(String name) {
        Categories categories = new Categories();
        categories.setName(name);
        categoriesRepo.save(categories);
    }

    @Override
    public void readCategory() {

    }

    @Override
    public void updateTransaction() {

    }

    @Override
    public void deleteTransaction() {

    }
}
