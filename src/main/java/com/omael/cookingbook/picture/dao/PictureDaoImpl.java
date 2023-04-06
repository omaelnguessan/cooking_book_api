package com.omael.cookingbook.picture.dao;

import com.omael.cookingbook.picture.Picture;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PictureDaoImpl implements PictureDao {
    private final JdbcTemplate jdbcTemplate;
    public PictureDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean existCoverByRecipeId(Integer id) {
        String sql = "SELECT COUNT(*) FROM picture WHERE recipe_id = ?";
        Object[] args = new Object[] { id };
        List<Integer> results = jdbcTemplate.query(sql,  new SingleColumnRowMapper<>(Integer.class), args);
        return !results.isEmpty() && results.get(0) > 0;
    }

    @Override
    public void disableCoverByRecipeId(Integer id) {
        String sql = "UPDATE picture SET cover = ? WHERE recipe_id = ?";
        Object[] args = new Object[] { true, id };
        jdbcTemplate.update(sql, args);
    }

    @Override
    public List<Picture> getPictureByRecipeId(Integer id) {
        String sql = "SELECT * FROM picture WHERE recipe_id = ?";
        Object[] args = new Object[] { id };
        return jdbcTemplate.query(sql,  new SingleColumnRowMapper<>(Picture.class), args);
    }
}
