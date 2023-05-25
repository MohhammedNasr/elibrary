package com.project.elibrary.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public  Book getBookById(Long bookID)
    {
        return bookRepository.findById(bookID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book ID: " + bookID));
    }

    //admin
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

    // @Override
    // public boolean adminDeleteBook(Long bookId) {
    //     Optional<Borrow> optionalBook = borrowRepository.findById(bookId);
    //     Borrow borrow = optionalBook.get();
    //     try {
    //         Book book = bookRepository.findById(bookId).orElse(null);
    //         if (book != null) {
    //             borrow.setUser(null);
    //             borrowRepository.save(borrow);
    //             bookRepository.delete(book);
    //             return true;
    //         }
    //         return false;
    //     } catch (Exception e) {
    //         return false;
    //     }
    // }
}
