package com.omael.cookingbook.recipe.dto;

import com.omael.cookingbook.category.Category;
import com.omael.cookingbook.category.dto.CategoryDTO;
import com.omael.cookingbook.recipe.RecipeLevel;
import com.omael.cookingbook.user.User;
import com.omael.cookingbook.user.dto.UserDto;

public record RecipeDTO (
         Integer id,
         String name,
         String description,
         RecipeLevel recipeLevel,
         Integer prepareTime,
         Integer cookingTime,
         Integer totalTime,
         Integer userId,
         Integer categoryId
) { }
