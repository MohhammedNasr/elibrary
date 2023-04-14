package com.project.elibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.elibrary.dao.FavoriteService;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Void> saveFavorite(
            @RequestParam(value = "id", required = false) String bookId,
            @RequestParam(value ="name", required = false) String bookName,
            @RequestParam(value ="authors", required = false) String authors,
            @RequestParam(value ="image", required = false) String image) {
        favoriteService.saveFavorite(bookId, bookName, authors, image);
        return ResponseEntity.ok().build();
    }
}

