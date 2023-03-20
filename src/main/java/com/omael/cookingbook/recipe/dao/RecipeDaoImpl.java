package com.omael.cookingbook.recipe.dao;

import com.omael.cookingbook.category.dao.CategoryRowMapper;
import com.omael.cookingbook.recipe.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    public RecipeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Recipe findRecipeByIdAndUserId(Integer id, Integer userId) {
        String sql = "SELECT * FROM recipe WHERE id = ? and user_id = ?";
        Object[] args = new Object[] { id, userId };
        return jdbcTemplate.queryForObject(sql, new RecipeRowMapper(), args);
    }
}
