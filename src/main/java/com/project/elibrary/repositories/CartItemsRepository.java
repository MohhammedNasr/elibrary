package com.project.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.CartItems;

public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
}