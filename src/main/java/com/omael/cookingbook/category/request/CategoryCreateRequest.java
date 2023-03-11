package com.omael.cookingbook.category.request;

public record CategoryCreateRequest(
        String name,
        String description,
        boolean status
) {
}
