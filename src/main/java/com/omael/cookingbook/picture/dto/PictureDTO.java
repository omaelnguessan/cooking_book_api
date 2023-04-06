package com.omael.cookingbook.picture.dto;

public record PictureDTO(
        Integer id,
        String name,
        String path,
        String description,
        Boolean cover
) { }
