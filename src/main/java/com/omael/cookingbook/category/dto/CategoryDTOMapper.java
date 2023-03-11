package com.omael.cookingbook.category.dto;

import com.omael.cookingbook.category.Category;
import com.omael.cookingbook.category.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isStatus()
        );
    }
}
