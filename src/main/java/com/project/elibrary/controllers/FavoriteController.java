package com.project.elibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.elibrary.dao.FavoriteService;
import com.project.elibrary.models.Favorite;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    //favorite list for a specific user
    @GetMapping("/{username}")
    public String getFavoritesByUsername(@PathVariable String username, Model model) {
        List<Favorite> favorites = favoriteService.getFavoritesByUsername(username);
        model.addAttribute("favorites", favorites);
        model.addAttribute("username", username);
        
        return "favorites.html";
    }

    //adding book to favorite list
    @PostMapping("/add")
    public ResponseEntity<Void> saveFavorite(
            @RequestParam(value = "id", required = false) String bookId,
            @RequestParam(value ="name", required = false) String bookName,
            @RequestParam(value ="authors", required = false) String authors,
            @RequestParam(value ="image", required = false) String image, 
            @RequestParam(value ="userName",required = false) String userName){
        favoriteService.saveFavorite(bookId, bookName, authors, image,userName);
        return ResponseEntity.ok().build();
    }

    //removing book from favorite list
    @PostMapping("/remove/{username}/{bookName}")
    public ResponseEntity<String> removeFavorite(@PathVariable String username, @PathVariable String bookName) {
        boolean removed = favoriteService.removeFavorite(username, bookName);
        if (removed) {
            return ResponseEntity.ok("Removed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't remove book from favorite list");
        }
    }
}

