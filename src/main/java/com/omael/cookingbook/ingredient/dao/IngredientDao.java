package com.omael.cookingbook.ingredient.dao;

import com.omael.cookingbook.ingredient.Ingredient;

public interface IngredientDao {
    Boolean existsByName(String name);
}
