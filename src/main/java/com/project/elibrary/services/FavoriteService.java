package com.project.elibrary.services;

import java.util.List;
import com.project.elibrary.models.Favorite;
import com.project.elibrary.models.User;

public interface FavoriteService {
    void saveFavorite(String bookName, String authors, String image, User user);
    List<Favorite> getFavoritesByUserID(Long userID);
    boolean removeFavorite(User user, String bookName);
}
