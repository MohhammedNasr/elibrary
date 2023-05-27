package com.project.elibrary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.elibrary.models.Cart;
import com.project.elibrary.models.CartItems;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.CartItemsRepository;
import com.project.elibrary.repositories.CartRepository;

@Service
public class CartService {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    public void createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalPrice(0.0);
        cart.setCartItems(new ArrayList<>());

        cartRepository.save(cart);
    }

    public Cart getUserCart(User user) {
        return user.getCart();
    }

    @Transactional
    public void addItemToCart(String bookName, String thumbnailUrl, double price, User user) {
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart.setTotalPrice(price);
            user.setCart(cart);
        } else {
            double totalPrice = cart.getTotalPrice() + price;
            cart.setTotalPrice(totalPrice);

            // Check if the book already exists in the cart
            Optional<CartItems> existingCartItem = cart.getCartItems()
                    .stream()
                    .filter(item -> item.getTitle().equals(bookName))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                CartItems cartItem = existingCartItem.get();
                int quantity = cartItem.getQuantity() + 1;
                cartItem.setQuantity(quantity);
            } else {
                CartItems cartItem = new CartItems();
                cartItem.setTitle(bookName);
                cartItem.setThumbnailUrl(thumbnailUrl);
                cartItem.setPrice(price);
                cartItem.setQuantity(1);
                cartItem.setCart(cart);
                cart.getCartItems().add(cartItem);
            }
        }

        cartRepository.save(cart);
        cartItemsRepository.saveAll(cart.getCartItems());
    }

    private double calculateTotalPrice(Cart cart) {
        List<CartItems> cartItems = cart.getCartItems();
        double totalPrice = 0.0;
        for (CartItems cartItem : cartItems) {
            int quantity = cartItem.getQuantity();
            if (quantity > 0) {
                totalPrice += cartItem.getPrice() * quantity;
            }
        }
        return totalPrice;
    }

    public void updateCartItemQuantity(Long cartItemId, int quantity) {
        CartItems cartItem = cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart item ID"));

        cartItem.setQuantity(quantity);
        cartItemsRepository.save(cartItem);

        // Recalculate the total price of the cart
        Cart cart = cartItem.getCart();
        double totalPrice = calculateTotalPrice(cart);
        cart.setTotalPrice(totalPrice);
        cartRepository.save(cart);

    }

    public void removeCartItem(Long cartItemId) {
        CartItems cartItem = cartItemsRepository.findById(cartItemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart item ID: " + cartItemId));

        Cart cart = cartItem.getCart();
        cart.getCartItems().remove(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() - (cartItem.getPrice() * cartItem.getQuantity()));

        cartItemsRepository.delete(cartItem);
        cartRepository.save(cart);
    }

}
