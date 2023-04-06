package com.omael.cookingbook.picture.dao;

import com.omael.cookingbook.picture.Picture;

import java.util.List;

public interface PictureDao {
    boolean existCoverByRecipeId(Integer id);
    void disableCoverByRecipeId(Integer id);
    List<Picture> getPictureByRecipeId(Integer id);
}
