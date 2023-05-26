package com.project.elibrary.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.elibrary.models.Cart;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.CartRepository;
import com.project.elibrary.repositories.UserRepo;

public class CartService
{
    @Autowired 
    private CartRepository cartRepository ; 
    private UserRepo userRepository ; 
    public void addToCart(String bookName,String image, User user,
         double price) 
         {
        
            
             Cart cart = new Cart();
             cart.setBookName(bookName);
             cart.setThumbnailUrl(image);
             cart.setUser(user);
           
             cart.setPrice(price);
             cartRepository.save(cart);
        user.addtoCart(cart); 
             userRepository.save(user);
             cartRepository.save(cart);
            }
       
        
    
    /*public List<Cart> findCartByUserId (Long userId)
    {
        return cartRepository.findByUserId(userId);
    }*/
    /*boolean removeFromCart(User user, String bookName,double price)
    {
        Optional <Cart> cartOptional = cartRepository.findByUserAndTitleAndPrice(user, bookName,price);
        if(cartOptional.isPresent())
        {
            Cart cart =cartOptional.get(); 
            cartRepository.delete(cart);
            return true ; 
        }
        return false ; 

    }*/
}
