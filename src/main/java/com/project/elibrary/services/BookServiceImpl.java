package com.project.elibrary.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.elibrary.models.Book;
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
    public Book createBook(String title, String description, List<String> authors, String thumbnailUrl, Boolean availability, Boolean reviewed, Long userID) {
        Book book = new Book(title, description, authors, thumbnailUrl, userID);
        book.setAvailability(availability);
        book.setReviewed(reviewed);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBooksByUserID(Long userID) {
        return bookRepository.findByUserID(userID);
    }

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
    public  Book getBookById(Long bookID)
    {
        return bookRepository.findById(bookID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + bookID));
    }
}
