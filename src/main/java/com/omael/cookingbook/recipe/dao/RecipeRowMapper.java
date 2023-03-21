package com.omael.cookingbook.recipe.dao;

import com.omael.cookingbook.category.Category;
import com.omael.cookingbook.recipe.Recipe;
import com.omael.cookingbook.recipe.RecipeLevel;
import com.omael.cookingbook.user.User;
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
        recipe.setRecipeLevel(RecipeLevel.valueOf(rs.getString("recipe_level")));
        recipe.setPrepareTime(rs.getInt("prepare_time"));
        recipe.setCookingTime(rs.getInt("cooking_time"));
        recipe.setTotalTime(rs.getInt("total_time"));

        User user = new User();
        user.setId((rs.getInt("user_id")));
        recipe.setUser(user);

        Category category = new Category();
        category.setId(rs.getInt("category_id"));
        recipe.setCategory(category);

        return recipe;
    }
}
