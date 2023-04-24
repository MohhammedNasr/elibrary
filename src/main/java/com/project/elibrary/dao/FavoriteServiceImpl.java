package com.project.elibrary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import com.project.elibrary.models.Favorite;
import com.project.elibrary.repositories.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void saveFavorite(String bookId, String bookName, String authors, String image,String userName) {
        Favorite favorite = new Favorite();
        favorite.setBookId(bookId);
        favorite.setBookName(bookName);
        favorite.setAuthors(authors);
        favorite.setImage(image);
        favorite.setUsername(userName);

        favoriteRepository.save(favorite);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Favorite> getFavoritesByUsername(String username) {
        String sql = "SELECT * FROM favorites WHERE user_name = ?";
        List<Favorite> favorites = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, username);
            }
        }, new RowMapper<Favorite>() {
            @Override
            public Favorite mapRow(ResultSet rs, int rowNum) throws SQLException {
                Favorite favorite = new Favorite();
                favorite.setId(rs.getLong("id"));
                favorite.setBookId(rs.getString("book_id"));
                favorite.setBookName(rs.getString("book_name"));
                favorite.setAuthors(rs.getString("authors"));
                favorite.setImage(rs.getString("image"));
                favorite.setUsername(rs.getString("user_name"));
                return favorite;
            }
        });
        return favorites;
    }
}
