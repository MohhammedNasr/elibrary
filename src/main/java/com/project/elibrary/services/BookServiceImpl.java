package com.project.elibrary.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.elibrary.models.Book;
import com.project.elibrary.repositories.BookRepository;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book createBook(String title, String description, List<String> authors, String thumbnailUrl, Boolean availability) {
        Book book = new Book(title, description, authors, thumbnailUrl);
        book.setAvailability(availability);
        return bookRepository.save(book);
    }
}
