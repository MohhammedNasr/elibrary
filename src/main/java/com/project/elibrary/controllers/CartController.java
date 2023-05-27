package com.project.elibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import com.project.elibrary.models.Cart;
import com.project.elibrary.models.CartItems;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.CartRepository;
import com.project.elibrary.services.CartService;

@Controller
@RequestMapping("/Cart")
public class CartController
 {
    @Autowired
    private CartService cartService;

   

    @GetMapping("/show")
    public ModelAndView showCart(@AuthenticationPrincipal User user, Model model) {
        Cart cart = cartService.getCartForUser(user.getId());

        List<CartItems> cartItems = cart.getCartItems();
     
        ModelAndView mav = new ModelAndView("Cart");  
        mav.addObject("cartItems", cartItems);
     
        return mav;     
    }

}
