package com.project.elibrary.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.elibrary.entity.Favorite;
import com.project.elibrary.repository.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public void saveFavorite(String bookId, String bookName, String authors, String image) {
        Favorite favorite = new Favorite();
        favorite.setBookId(bookId);
        favorite.setBookName(bookName);
        favorite.setAuthors(authors);
        favorite.setImage(image);

        favoriteRepository.save(favorite);
    }
}

