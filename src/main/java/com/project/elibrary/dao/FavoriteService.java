package com.project.elibrary.dao;

import java.util.List;
import com.project.elibrary.models.Favorite;

public interface FavoriteService {
    void saveFavorite(String bookName, String authors, String image, Long userID);
    List<Favorite> getFavoritesByUserID(Long userID);
    boolean removeFavorite(Long userID, String bookName);
}
