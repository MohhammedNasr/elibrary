package com.project.elibrary.services;

import java.util.List;
import com.project.elibrary.models.Book;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getReviewedBooks();
    List<Book> getBooksByUserID(Long userID);

    Book createBook(String title, String description, List<String> authors, String thumbnailUrl, Boolean availability, Boolean reviewed, Long userID);
    Book getBookById(Long bookID); 
  
    void acceptBook(Long bookID);
    void rejectBook(Long bookID);
    boolean adminEditBook(Long bookId, String thumbnail, String title, String description);
    boolean adminDeleteBook(Long bookId);
}

