package com.budgetyoufool.repositorys;

import com.budgetyoufool.entitis.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {
    Categories findCategoriesByName(String name);
}
