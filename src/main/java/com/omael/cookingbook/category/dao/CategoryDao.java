package com.omael.cookingbook.category.dao;

import com.omael.cookingbook.category.Category;

import java.util.Optional;

public interface CategoryDao {
    boolean existsCategoryByName(String name);
    Optional<Category> selectCategoryByName(String name);
}
