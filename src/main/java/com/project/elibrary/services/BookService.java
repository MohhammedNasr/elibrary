package com.project.elibrary.services;

import java.util.List;
import com.project.elibrary.models.Book;

public interface BookService {
    List<Book> getAllBooks();
    Book createBook(String title, String description, List<String> authors, String thumbnailUrl, Boolean availability);
}

