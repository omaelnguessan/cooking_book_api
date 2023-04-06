package com.omael.cookingbook.picture.dto;

import com.omael.cookingbook.picture.Picture;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PictureDTOMapper implements Function<Picture, PictureDTO> {
    @Override
    public PictureDTO apply(Picture picture) {
        return new PictureDTO(
                picture.getId(),
                picture.getName(),
                picture.getPath(),
                picture.getDescription(),
                picture.isCover()
        );
    }
}
