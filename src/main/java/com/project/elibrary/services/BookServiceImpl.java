package com.project.elibrary.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import com.project.elibrary.googleBooks.GoogleBook;
import com.project.elibrary.googleBooks.GoogleBooksResponse;
import com.project.elibrary.googleBooks.GoogleImageLinks;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getReviewedBooks() {
        return bookRepository.findByReviewed(true);
    }

    @Override
    public Book createBook(String title, String description, List<String> authors, String thumbnailUrl,
            Boolean availability, Boolean reviewed, User user) {
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        book.setAuthors(authors);
        book.setThumbnailUrl(thumbnailUrl);
        book.setAvailability(availability);
        book.setReviewed(reviewed);
        book.setUser(user);

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksByUserID(Long userID) {
        return bookRepository.findByUser_Id(userID);
    }

    @Override
    public Book getBookById(Long bookID) {
        return bookRepository.findById(bookID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + bookID));
    }

    // admin
    @Override
    public void acceptBook(Long bookID) {
        Optional<Book> optionalBook = bookRepository.findById(bookID);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setReviewed(true);
            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Invalid book ID: " + bookID);
        }
    }

    @Override
    public void rejectBook(Long bookID) {
        Optional<Book> optionalBook = bookRepository.findById(bookID);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setReviewed(false);
            bookRepository.save(book);
        } else {
            throw new IllegalArgumentException("Invalid book ID: " + bookID);
        }
    }

    @Override
    public boolean adminEditBook(Long bookId, String thumbnail, String title, String description) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            book.setThumbnailUrl(thumbnail);
            book.setTitle(title);
            book.setDescription(description);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean adminDeleteBook(Long bookId) {
        try {
            Book book = bookRepository.findById(bookId).orElse(null);
            if (book != null) {
                bookRepository.delete(book);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void searchBooks(String query, Model model) {
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
    }

    @Override
    public void homePageBooks(String query, Model model){
        String url = "https://www.googleapis.com/books/v1/volumes?q={bookName}";
        GoogleBooksResponse response = restTemplate.getForObject(url, GoogleBooksResponse.class, query);
    
        List<Book> books = new ArrayList<>();
        
        int limit = 4; // Limit the number of books to be shown
        
        for (int i = 0; i < response.getItems().size() && i < limit; i++) {
            GoogleBook googleBook = response.getItems().get(i);
            
            Book book = new Book();
            book.setTitle(googleBook.getVolumeInfo().getTitle());
            book.setDescription(googleBook.getVolumeInfo().getDescription());
            book.setAuthors(googleBook.getVolumeInfo().getAuthors());
            book.setThumbnailUrl(googleBook.getVolumeInfo().getImageLinks().getThumbnail());
            book.setPageCount(googleBook.getVolumeInfo().getPageCount());
            book.setPublishedDate(googleBook.getVolumeInfo().getPublishedDate());
            book.setAverageRating(googleBook.getVolumeInfo().getAverageRating());
            book.setPrice(googleBook.getVolumeInfo().getPrice());

            books.add(book);
        }

        model.addAttribute("books", books);
    }

    @Override
    public void fetchBookDetails(String bookName, Model model) {
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
    }
}
