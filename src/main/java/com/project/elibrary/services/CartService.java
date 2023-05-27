package com.project.elibrary.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elibrary.models.CartItems;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.CartItemsRepository;

@Service
public class CartService {
    @Autowired
    private CartItemsRepository  cartItemsRepository;



    @Transactional
    public boolean addItem(String bookName, String image, User user, double price) {
        
        Optional<CartItems> existingItem = cartItemsRepository.findByBookNameAndUser(bookName, user);
        
        if (existingItem.isPresent()) {
    
           existingItem.get().setQuantity(existingItem.get().getQuantity() + 1);
           cartItemsRepository.save(existingItem.get());
        } else {
           // Create new item
           CartItems item = new CartItems();
           item.setBookName(bookName);
           item.setThumbnailUrl(image);
           item.setPrice(price);
           item.setUser(user);
           item.setQuantity(1);
           cartItemsRepository.save(item);
        }
        
        return true; 
    }

   /*  public Cart getCartForUser(Long userId) {
        User user = userRepository.findById(userId).get();
        return cartRepository.findByUser(user);
    }*/

}
