package com.omael.cookingbook.ingredient.dto;

import java.time.LocalDateTime;

public record IngredientDTO(
        Integer id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
