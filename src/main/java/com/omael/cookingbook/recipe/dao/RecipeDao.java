package com.omael.cookingbook.recipe.dao;

import com.omael.cookingbook.recipe.Recipe;

public interface RecipeDao {
    Recipe findRecipeByIdAndUserId(Integer id,Integer userId);
}