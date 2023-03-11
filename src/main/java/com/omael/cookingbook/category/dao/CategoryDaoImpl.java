package com.omael.cookingbook.category.dao;

import com.omael.cookingbook.category.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsCategoryByName(String name) {
        String sql = "SELECT COUNT(*) FROM category WHERE name = ?";
        Object[] args = new Object[] { name };
        List<Integer> results = jdbcTemplate.query(sql,  new SingleColumnRowMapper<>(Integer.class), args);
        return !results.isEmpty() && results.get(0) > 0;
    }

    @Override
    public Optional<Category> selectCategoryByName(String name) {
        String sql = "SELECT * FROM category WHERE name = ?";
        Object[] args = new Object[] { name };
        Category category = jdbcTemplate.queryForObject(sql, new CategoryRowMapper(), args);
        return Optional.ofNullable(category);
    }
}
