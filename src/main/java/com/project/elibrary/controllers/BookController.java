package com.project.elibrary.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.User;
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
      book.setPrice(googleBook.getVolumeInfo().getPrice());
    
      model.addAttribute("book", book);
      return "bookmDetails";
    }

    @Autowired
    private BookService bookService;

    //adding book manually (donating)
    @PostMapping("/add")
    public String createBook(Book book, @AuthenticationPrincipal User user) {
        book.setAvailability(true); // When any book gets added, it is set to be available 
        book.setReviewed(false); // When any book gets added, it is set to be not reviewed and waiting for admin review
        bookService.createBook(book.getTitle(), book.getDescription(), book.getAuthors(), book.getThumbnailUrl(), book.getAvailability(), book.getReviewed(), user);
        // Redirect to the profile.html page
        return "redirect:/library/profile";
    }

     //view list of donated books by userID
    @GetMapping("/donated") 
    public ModelAndView showBooks(@AuthenticationPrincipal User user) {
        Long userID = user.getId();
        List<Book> books = bookService.getBooksByUserID(userID);
        ModelAndView mav = new ModelAndView("donatedBooks");
        mav.addObject("books", books);
        return mav;
    }
}
