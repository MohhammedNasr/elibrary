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

import com.project.elibrary.models.Cart;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.UserRepo;
import com.project.elibrary.services.CartService;

@Controller
@RequestMapping("/library")
public class CartController {
    @Autowired
    private UserRepo userRepository;
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<Void> addToCart(@RequestParam("bookName") String bookName,
            @RequestParam("image") String image,
            @RequestParam("price") double price,
            @AuthenticationPrincipal User user) {

        cartService.addToCart(bookName, image, user, price);
        return ResponseEntity.ok().build();
    }

   /*  @GetMapping("/showCart")
    public ModelAndView showCart(@AuthenticationPrincipal User user) {
        Long userID = user.getId();
        List<Cart> cartItems = cartService.findCartByUserId(userID);
        ModelAndView mav = new ModelAndView("Cart");
        mav.addObject("cartItems", cartItems);
        return mav;
    }*/
}
