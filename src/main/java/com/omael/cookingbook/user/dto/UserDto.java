package com.omael.cookingbook.user.dto;

public record UserDto(
        Integer id,
        String firstName,
        String lastName,
        String email
) {
}
