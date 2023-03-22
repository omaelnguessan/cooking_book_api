package com.omael.cookingbook.ingredient.dto;

import com.omael.cookingbook.ingredient.Ingredient;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class IngredientDTOMapper implements Function<Ingredient, IngredientDTO> {
    @Override
    public IngredientDTO apply(Ingredient ingredient) {
        return new IngredientDTO(
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getCreatedAt(),
                ingredient.getUpdatedAt()
        );
    }
}
