package com.project.elibrary.dao;

import java.util.List;

import com.project.elibrary.models.Favorite;

public interface FavoriteService {
    void saveFavorite(String bookId, String bookName, String authors, String image, String userName);
    List<Favorite> getFavoritesByUsername(String username);
    boolean removeFavorite(String username, String bookName);
}
