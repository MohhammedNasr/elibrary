package com.project.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.Cart;
import com.project.elibrary.models.User;
public interface  CartRepository  extends JpaRepository<Cart, Long> 
{
   Cart findByUser(User user);
}
