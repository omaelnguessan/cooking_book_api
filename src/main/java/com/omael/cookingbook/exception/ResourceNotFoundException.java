package com.omael.cookingbook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Throwable {
    public ResourceNotFoundException(String formatted) {
        super(formatted);
    }
}
