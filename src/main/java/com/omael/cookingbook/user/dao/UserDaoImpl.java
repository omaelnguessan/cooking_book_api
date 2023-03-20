package com.omael.cookingbook.user.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
        Object[] args = new Object[] { email };
        List<Integer> results = jdbcTemplate.query(sql,  new SingleColumnRowMapper<>(Integer.class), args);
        return !results.isEmpty() && results.get(0) > 0;
    }
}
