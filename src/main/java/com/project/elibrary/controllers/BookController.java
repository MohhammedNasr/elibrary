package com.project.elibrary.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.project.elibrary.models.Book;
import com.project.elibrary.googleBooks.GoogleBook;
import com.project.elibrary.googleBooks.GoogleBooksResponse;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        String url = "https://www.googleapis.com/books/v1/volumes?q={query}";
        GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class, query);

        List<Book> books = new ArrayList<>();

        for (GoogleBook googleBook : response.getItems()) {
            Book book = new Book();
            book.setTitle(googleBook.getVolumeInfo().getTitle());
            book.setDescription(googleBook.getVolumeInfo().getDescription());
            book.setAuthors(googleBook.getVolumeInfo().getAuthors());
            book.setThumbnailUrl(googleBook.getVolumeInfo().getImageLinks().getThumbnail());
            books.add(book);
        }

        model.addAttribute("books", books);
        return "search";
    }

}
