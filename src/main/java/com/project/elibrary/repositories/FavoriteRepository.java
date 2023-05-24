package com.project.elibrary.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser_IdAndBookName(Long userId, String bookName);
}


