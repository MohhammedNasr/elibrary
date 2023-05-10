package com.project.elibrary.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.project.elibrary.models.Book;
import com.project.elibrary.services.BookService;
import com.project.elibrary.googleBooks.GoogleBook;
import com.project.elibrary.googleBooks.GoogleBooksResponse;
import com.project.elibrary.googleBooks.GoogleImageLinks;

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
            GoogleImageLinks imageLinks = googleBook.getVolumeInfo().getImageLinks();
            if (imageLinks != null) {
                book.setThumbnailUrl(imageLinks.getThumbnail());
            }
            books.add(book);
        }

        model.addAttribute("books", books);
        return "search";
    }

    @GetMapping("/details")
    public String showBookDetails(@RequestParam("bookName") String bookName, Model model) {
      String url = "https://www.googleapis.com/books/v1/volumes?q={bookName}";
      GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class, bookName);
    
      GoogleBook googleBook = response.getItems().get(0);
      Book book = new Book();
      book.setTitle(googleBook.getVolumeInfo().getTitle());
      book.setDescription(googleBook.getVolumeInfo().getDescription());
      book.setAuthors(googleBook.getVolumeInfo().getAuthors());
      book.setThumbnailUrl(googleBook.getVolumeInfo().getImageLinks().getThumbnail());
      
      // Set the page count, published date, and average rating of the book
      book.setPageCount(googleBook.getVolumeInfo().getPageCount());
      book.setPublishedDate(googleBook.getVolumeInfo().getPublishedDate());
      book.setAverageRating(googleBook.getVolumeInfo().getAverageRating());
    
      model.addAttribute("book", book);
      return "bookDetails";
    }


    //to see the book page
    @GetMapping("/mdetails")
    public String showBookmDetails(@RequestParam("bookName") String bookName, Model model) {
      String url = "https://www.googleapis.com/books/v1/volumes?q={bookName}";
      GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class, bookName);
    
      GoogleBook googleBook = response.getItems().get(0);
      Book book = new Book();
      book.setTitle(googleBook.getVolumeInfo().getTitle());
      book.setDescription(googleBook.getVolumeInfo().getDescription());
      book.setAuthors(googleBook.getVolumeInfo().getAuthors());
      book.setThumbnailUrl(googleBook.getVolumeInfo().getImageLinks().getThumbnail());
      
      // Set the page count, published date, and average rating of the book
      book.setPageCount(googleBook.getVolumeInfo().getPageCount());
      book.setPublishedDate(googleBook.getVolumeInfo().getPublishedDate());
      book.setAverageRating(googleBook.getVolumeInfo().getAverageRating());
    
      model.addAttribute("book", book);
      return "bookmDetails";
    }

    
    @Autowired
    private BookService bookService;

    //adding book manually (donating)
    @PostMapping("/add")
    public ResponseEntity<Book> createBook(Book book) {
        book.setAvailability(true); //when any book get added its set to be available 
        Book createdBook = bookService.createBook(book.getTitle(), book.getDescription(), book.getAuthors(), book.getThumbnailUrl(), book.getAvailability());
        return ResponseEntity.created(URI.create("/books/" + createdBook.getId())).body(createdBook);
    }
    

    //view list of uploaded books/donated books/created books
    @GetMapping("/allbooks")
    public ModelAndView showBooks() {
        List<Book> books = bookService.getAllBooks();
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books", books);
        return mav;
    }

}
