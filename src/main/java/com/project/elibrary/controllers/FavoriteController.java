package com.project.elibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.elibrary.models.Favorite;
import com.project.elibrary.models.User;
import com.project.elibrary.services.FavoriteService;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    // favorite list for a specific user
    @GetMapping("/list")
    public String getFavoritesByUserID(@AuthenticationPrincipal User user, Model model) {
        Long userID = user.getId();
        List<Favorite> favorites = favoriteService.getFavoritesByUserID(userID);
        model.addAttribute("favorites", favorites);
        model.addAttribute("userID", userID);
        return "favorites";
    }

    // adding book to favorite list
    @PostMapping("/add")
    public ResponseEntity<Void> saveFavorite(
            @RequestParam(value = "name", required = false) String bookName,
            @RequestParam(value = "authors", required = false) String authors,
            @RequestParam(value = "image", required = false) String image,
            @AuthenticationPrincipal User user) {
        Long userID = user.getId();
        favoriteService.saveFavorite(bookName, authors, image, userID);
        return ResponseEntity.ok().build();
    }

    // removing book from favorite list
    @PostMapping("/remove/{userID}/{bookName}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long userID, @PathVariable String bookName) {
        boolean removed = favoriteService.removeFavorite(userID, bookName);
        if (removed) {
            return ResponseEntity.ok("Removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Couldn't remove book from favorite list");
        }
    }
}
