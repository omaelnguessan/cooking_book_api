package com.omael.cookingbook.ingredient.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IngredientDaoImpl implements IngredientDao {
    private final JdbcTemplate jdbcTemplate;

    public IngredientDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) FROM ingredient WHERE name = ?";
        Object[] args = new Object[] { name };
        List<Integer> results = jdbcTemplate.query(sql,  new SingleColumnRowMapper<>(Integer.class), args);
        return !results.isEmpty() && results.get(0) > 0;
    }
}
