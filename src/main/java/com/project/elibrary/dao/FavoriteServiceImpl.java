package com.project.elibrary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
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
    public void saveFavorite(String bookName, String authors, String image, Long userID) {
        Optional<Favorite> favoriteOptional = favoriteRepository.findByUserIDAndBookName(userID, bookName); //checking if the book is already added or not
        if (!favoriteOptional.isPresent()) {
            Favorite favorite = new Favorite();
            favorite.setBookName(bookName);
            favorite.setAuthors(authors);
            favorite.setImage(image);
            favorite.setUserID(userID);

            favoriteRepository.save(favorite);
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Favorite> getFavoritesByUserID(Long userID) {
        String sql = "SELECT * FROM favorites WHERE user_id = ?";
        List<Favorite> favorites = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, userID);
            }
        }, new RowMapper<Favorite>() {
            @Override
            public Favorite mapRow(ResultSet rs, int rowNum) throws SQLException {
                Favorite favorite = new Favorite();
                favorite.setId(rs.getLong("id"));
                favorite.setBookName(rs.getString("book_name"));
                favorite.setAuthors(rs.getString("authors"));
                favorite.setImage(rs.getString("image"));
                favorite.setUserID(userID);
                return favorite;
            }
        });
        return favorites;
    }

    @Override
    public boolean removeFavorite(Long userID, String bookName) {
        Optional<Favorite> favorite = favoriteRepository.findByUserIDAndBookName(userID, bookName);
        if (favorite.isPresent()) {
            favoriteRepository.delete(favorite.get());
            return true;
        } else {
            return false;
        }
    }

}
