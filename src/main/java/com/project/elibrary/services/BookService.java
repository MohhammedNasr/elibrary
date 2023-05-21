package com.project.elibrary.services;

import java.util.List;
import com.project.elibrary.models.Book;

public interface BookService {
    List<Book> getAllBooks();
    List<Book> getReviewedBooks();
    Book createBook(String title, String description, List<String> authors, String thumbnailUrl, Boolean availability, Boolean reviewed, Long userID);
    List<Book> getBooksByUserID(Long userID);
    void acceptBook(Long bookID);
    void rejectBook(Long bookID);
    Book getBookById(Long bookID); 
}

