package com.project.elibrary.services;

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
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.FavoriteRepository;
import com.project.elibrary.repositories.UserRepo;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveFavorite(String bookName, String authors, String image, User user) {
        Long userID = user.getId();
        List<Favorite> favoriteOptional = favoriteRepository.findByUser_IdAndBookName(userID, bookName); //checking if the book is already added or not
        if (favoriteOptional.isEmpty()) {
            Favorite favorite = new Favorite();
            favorite.setBookName(bookName);
            favorite.setAuthors(authors);
            favorite.setImage(image);
            favorite.setUser(user);
            favoriteRepository.save(favorite);
            user.addToFavorites(favorite);
            userRepo.save(user);
            favoriteRepository.save(favorite);
        }
    }

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
                return favorite;
            }
        });
        return favorites;
    }

    @Override
    public boolean removeFavorite(User user, String bookName) {
        Long userID = user.getId();
        List<Favorite> favorite = favoriteRepository.findByUser_IdAndBookName(userID, bookName);
        if (!favorite.isEmpty()) {
            favoriteRepository.delete(favorite.get(0));
            return true;
        } else {
            return false;
        }
    }

}
