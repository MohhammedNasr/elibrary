package com.project.elibrary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.elibrary.models.Cart;
@Repository
public interface  CartRepository  extends JpaRepository<Cart, Long> 
{
   List<Cart> findByUser_IdAndBookName(Long userId, String bookName);
   List<Cart> findByUser_Id(Long userId); 
}
