package com.project.elibrary.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.elibrary.models.CartItems;
import com.project.elibrary.models.User;
@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Long> {
   
    Optional<CartItems> findByBookNameAndUser(String bookName, User user);
    
 } 


