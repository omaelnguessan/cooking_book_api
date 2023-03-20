package com.omael.cookingbook.recipe.request;

import com.omael.cookingbook.category.Category;
import com.omael.cookingbook.recipe.RecipeLevel;
import com.omael.cookingbook.user.User;

public record CreateRecipeRequest(
        String name,
        String description,
        RecipeLevel recipeLevel,
        Integer prepareTime,
        Integer cookingTime,
        Integer totalTime,
        Integer category
        // TODO : images files
        // TODO : ingredients list
        // TODO : cooking steps
) { }
