package com.project.elibrary.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.elibrary.models.Book;
import com.project.elibrary.repositories.BookRepository;

@Service
public class BookService {

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
