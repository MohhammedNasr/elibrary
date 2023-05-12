package com.project.elibrary.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByUserIDAndBookName(Long userID, String bookName);
}
