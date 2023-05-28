package com.project.elibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.elibrary.models.User;
import com.project.elibrary.services.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Void> addItemToCart(
            @RequestParam("bookName") String bookName,
            @RequestParam("image") String image,
            @RequestParam("price") double price,
            @AuthenticationPrincipal User user) {
        cartService.addItemToCart(bookName, image, price, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public String updateCartItemQuantity(
            @RequestParam("cartItemId") Long cartItemId,
            @RequestParam("quantity") int quantity, Model model) {

        cartService.updateCartItemQuantity(cartItemId, quantity);
        model.addAttribute("message", "done");
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    @ResponseBody
    public ResponseEntity<?> removeCartItem(@RequestParam("cartItemId") Long cartItemId) {
        try {
            cartService.removeCartItem(cartItemId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}