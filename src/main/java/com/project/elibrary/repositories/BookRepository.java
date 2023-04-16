package com.project.elibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

