package com.project.elibrary.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elibrary.models.Book;
import com.project.elibrary.repositories.BookRepository;

@Service
public class BorrowService
 {
    @Autowired
    private BookRepository bookRepository ; 
    public List<Book> borrowBooks(List<Long> bookIds)
     {
        List<Book> books = bookRepository.findAllById(bookIds);   
        return books;
    }
  
    
}
