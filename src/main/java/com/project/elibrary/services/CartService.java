package com.project.elibrary.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.elibrary.models.Cart;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.CartRepository;
import com.project.elibrary.repositories.UserRepo;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepo userRepository;

    public boolean addToCart(String bookName, String image, User user, double price) {
        Long userID = user.getId();
        List<Cart> CartOptional = cartRepository.findByUser_IdAndBookName(userID, bookName);
        if (CartOptional.isEmpty()) {
            Cart cart = new Cart();
            cart.setBookName(bookName);
            cart.setThumbnailUrl(image);
            cart.setUser(user);
            cart.setPrice(price);
            cart.setQuantity(1);
            cart.setTotalPrice(cart.getPrice() * cart.getQuantity());
            cartRepository.save(cart);
            user.addToCart(cart);
            userRepository.save(user);
            cartRepository.save(cart);
            return true; // Book added to cart successfully
        } else {
            return false; // Book is already in the cart
        }

    }

    public List<Cart> getAllCartItems(Long userId) {
        return cartRepository.findByUser_Id(userId);
    }

}
