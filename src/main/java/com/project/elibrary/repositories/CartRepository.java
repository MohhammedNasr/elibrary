package com.project.elibrary.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.elibrary.models.Cart;
import com.project.elibrary.models.User;


public interface  CartRepository  extends JpaRepository<Cart, Long> 
{

   //List<Cart> findByUserId(Long userId);
   // Optional<Cart> findByUserAndTitleAndPrice(User user, String title, Double price);
}
