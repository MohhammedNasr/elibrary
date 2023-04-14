package com.project.elibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.entity.Favorite;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}

