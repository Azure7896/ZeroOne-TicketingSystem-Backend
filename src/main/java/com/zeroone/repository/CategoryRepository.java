package com.zeroone.repository;

import com.zeroone.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoriesById(Long id);

    Category findCategoriesByCategoryName(String name);

}
