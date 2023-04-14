package com.project.elibrary.dao;

public interface FavoriteService {
    void saveFavorite(String bookId, String bookName, String authors, String image);
}
