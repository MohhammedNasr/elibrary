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
import com.project.elibrary.models.User;
import com.project.elibrary.services.CartService;

@Controller
@RequestMapping("/Cart")
public class CartController
 {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam("bookName") String bookName,
            @RequestParam("image") String image,
            @RequestParam("price") double price,
            @AuthenticationPrincipal User user) {
        boolean bookAdded = cartService.addToCart(bookName, image, user, price);
        if (bookAdded) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Book is already in the cart.");
        }
    }

    @GetMapping("/showCart")
    public ModelAndView showCart(@AuthenticationPrincipal User user, Model model) {
        Long userID = user.getId();
        List<Cart> carts = cartService.getAllCartItems(userID);
        ModelAndView mav = new ModelAndView("Cart");
        mav.addObject("cart", carts);
        return mav;
    }

}
