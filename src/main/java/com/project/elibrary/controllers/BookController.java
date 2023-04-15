package com.project.elibrary.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.project.elibrary.models.Book;
import com.project.elibrary.dao.BookService;
import com.project.elibrary.dto.BookDTO;
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

    //adding book manually (donating)
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.createBook(bookDTO.getTitle(), bookDTO.getDescription(), bookDTO.getAuthors(), bookDTO.getThumbnailUrl(), bookDTO.getAvailability());
        return ResponseEntity.created(URI.create("/books/" + book.getId())).body(book);
    }

    @GetMapping("/allbooks")
    public ModelAndView showBooks() {
        List<Book> books = bookService.getAllBooks();
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books", books);
        return mav;
    }

}
