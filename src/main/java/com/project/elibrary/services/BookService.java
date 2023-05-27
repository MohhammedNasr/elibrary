package com.project.elibrary.services;

import java.util.List;
import org.springframework.ui.Model;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.User;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> getReviewedBooks();

    List<Book> getBooksByUserID(Long userID);

    void searchBooks(String query, Model model);

    void fetchBookDetails(String bookName, Model model);

    void homePageBooks(String query, Model model);

    Book createBook(String title, String description, List<String> authors, String thumbnailUrl, Boolean availability, Boolean reviewed, User user);

    Book getBookById(Long bookID);

    void acceptBook(Long bookID);

    void rejectBook(Long bookID);

    boolean adminEditBook(Long bookId, String thumbnail, String title, String description);

    boolean adminDeleteBook(Long bookId);
}
