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
        checkIfNewCategoryIsProper(name);
        Categories categories = new Categories();
        categories.setName(name);
        categoriesRepo.save(categories);
    }

    @Override
    public void readCategory() {

    }

    @Override
    public void updateCategory() {

    }

    @Override
    public void deleteCategory() {

    }

    private void checkIfNewCategoryIsProper(String name) {
        if (categoriesRepo.findCategoriesByName(name) != null) {
            throw new IllegalArgumentException("Such a category already exist!");
        } else if (name == null) {
            throw new NullPointerException("Name can't be null value!");
        } else if (name.isBlank()) {
            throw new IllegalArgumentException("Categories name cant be empty!");
        }
    }
}
