package com.omael.cookingbook.category.request;

public record CategoryUpdateRequest (
        String name,
        String description,
        boolean status
        ) { }
