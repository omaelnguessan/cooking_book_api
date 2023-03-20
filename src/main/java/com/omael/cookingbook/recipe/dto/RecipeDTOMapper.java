package com.omael.cookingbook.recipe.dto;

import com.omael.cookingbook.recipe.Recipe;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RecipeDTOMapper implements Function<Recipe, RecipeDTO> {
    @Override
    public RecipeDTO apply(Recipe recipe) {
        return new RecipeDTO(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getRecipeLevel(),
                recipe.getPrepareTime(),
                recipe.getCookingTime(),
                recipe.getTotalTime(),
                recipe.getUser(),
                recipe.getCategory()
        );
    }
}
