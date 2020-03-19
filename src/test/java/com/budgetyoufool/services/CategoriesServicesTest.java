package com.budgetyoufool.services;

import com.budgetyoufool.entitis.Categories;
import com.budgetyoufool.repositorys.CategoriesRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class CategoriesServicesTest {

    private CategoriesRepo categoriesRepo;
    private CategoriesServices categoriesServices;

    @BeforeEach
    void initializeCategories() {
        categoriesRepo = mock(CategoriesRepo.class);
        categoriesServices = new CategoriesServices(categoriesRepo);
    }

    @Test
    void shouldThrowExceptionWhenCategoryAlreadyExist() {

        initializeCategories();
        Categories categoriesMock = new Categories("Bills");

        when(categoriesRepo.findCategoriesByName("Bills")).thenReturn(categoriesMock);

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                categoriesServices.createCategory("Bills"));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameIsNullValue() {

        initializeCategories();

        Assertions.assertThrows(NullPointerException.class, () ->
                categoriesServices.createCategory(null));
    }

    @Test
    void shouldThrowExceptionWhenCategoryNameIsBlank() {

        initializeCategories();

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                categoriesServices.createCategory(""));

        Assertions.assertThrows(IllegalArgumentException.class, () ->
                categoriesServices.createCategory(" "));
    }
}