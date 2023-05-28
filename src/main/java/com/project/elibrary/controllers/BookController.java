package com.project.elibrary.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.User;
import com.project.elibrary.services.BookService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        bookService.searchBooks(query, model);
        return "search";
    }

    @GetMapping("/details")
    public String showBookDetails(@RequestParam("bookName") String bookName, Model model) {
        bookService.fetchBookDetails(bookName, model);
        return "bookDetails";
    }

    @GetMapping("/mdetails")
    public String showBookmDetails(@RequestParam("bookName") String bookName, Model model) {
        bookService.fetchBookDetails(bookName, model);
        return "bookmDetails";
    }

    //adding book (donating)
    @PostMapping("/add")
    public String createBook(Book book, @AuthenticationPrincipal User user) {
        book.setAvailability(true); // When any book gets added, it is set to be available
        book.setReviewed(false); // When any book gets added, it is set to be not reviewed and waiting for admin
        bookService.createBook(book.getTitle(), book.getDescription(), book.getAuthors(), book.getThumbnailUrl(),
                book.getAvailability(), book.getReviewed(), user);
        return "redirect:/library/profile"; // Redirect to the profile.html page
    }

    //home page category select
    @PostMapping("/category")
    public ResponseEntity<String> updateBookName(@RequestParam("book") String book, HttpSession session) {
        session.setAttribute("selectedBook", book); // Store the selected book name in a session attribute
        return ResponseEntity.ok().build();
    }

}
