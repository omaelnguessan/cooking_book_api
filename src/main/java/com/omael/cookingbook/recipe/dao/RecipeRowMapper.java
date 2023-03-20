package com.omael.cookingbook.recipe.dao;

import com.omael.cookingbook.recipe.Recipe;
import com.omael.cookingbook.recipe.RecipeLevel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeRowMapper implements RowMapper<Recipe> {
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt("id"));
        recipe.setName(rs.getString("name"));
        recipe.setDescription(rs.getString("description"));
        recipe.setRecipeLevel(RecipeLevel.values()[rs.getInt("recipeLevel")]);
        recipe.setPrepareTime(rs.getInt("prepareTime"));
        recipe.setCookingTime(rs.getInt("cookingTime"));
        recipe.setTotalTime(rs.getInt("totalTime"));
        recipe.getCategory().setId(rs.getInt("categoryId"));
        recipe.getUser().setId(rs.getInt("userId"));
        return recipe;
    }
}
