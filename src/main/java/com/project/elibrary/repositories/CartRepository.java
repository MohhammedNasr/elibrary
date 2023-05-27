package com.project.elibrary.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.Cart;
public interface  CartRepository  extends JpaRepository<Cart, Long> 
{
   List<Cart> findByUser_IdAndBookName(Long userId, String bookName);
   List<Cart> findByUser_Id(Long userId); 
}
