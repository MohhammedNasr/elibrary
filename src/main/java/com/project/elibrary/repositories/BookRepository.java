package com.project.elibrary.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUser_Id(Long userId);    
    List<Book> findByReviewed(Boolean reviewed);
}


