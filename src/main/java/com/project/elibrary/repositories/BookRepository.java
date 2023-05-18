package com.project.elibrary.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.elibrary.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserID(Long userID);
    List<Book> findByReviewed(Boolean reviewed);
    List<Book>findByBorrowerId(Long userId);//for borrowing history 
    List<Book> findBooksById(Long bookId); // for borrowing books 
    

}

