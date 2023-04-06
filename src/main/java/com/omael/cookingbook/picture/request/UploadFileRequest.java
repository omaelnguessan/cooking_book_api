package com.omael.cookingbook.picture.request;

import org.springframework.web.multipart.MultipartFile;

public record UploadFileRequest (
        MultipartFile picture,
        Integer recipeId
) {}
