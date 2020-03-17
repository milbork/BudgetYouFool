package com.budgetyoufool.services;

import com.budgetyoufool.entitis.Categories;
import com.budgetyoufool.repositorys.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServices {

    private final CategoriesRepo categoriesRepo;

    @Autowired
    public CategoriesServices(CategoriesRepo categoriesRepo) {
        this.categoriesRepo = categoriesRepo;
    }

    public void createNewCategory(String name) {
        Categories categories = new Categories();
        categories.setName(name);
        categoriesRepo.save(categories);
    }



}
