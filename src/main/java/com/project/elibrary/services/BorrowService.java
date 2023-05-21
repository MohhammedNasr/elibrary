package com.project.elibrary.services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.elibrary.models.Book;
import com.project.elibrary.models.Borrow;
import com.project.elibrary.repositories.BookRepository;
import com.project.elibrary.repositories.BorrowRepository;

@Service
public class BorrowService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;

    

    public void saveBorrow(Long bookId, Long userID, LocalDate startDate) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            String bookName = book.getTitle();
            String description = book.getDescription();
            String image = book.getThumbnailUrl();
            List<String> author = book.getAuthors();
    
            if (book.getAvailability()) {
                if (!book.isIsBorrowed()) {
                    Borrow borrowed = new Borrow();
                    borrowed.setBookId(bookId);
                    borrowed.setBookName(bookName);
                    borrowed.setAuthors(author);
                    borrowed.setDescription(description);
                    borrowed.setImage(image);
                    borrowed.setUserID(userID);
                    borrowed.setStartDate(startDate);
                    // borrowed.setEndDate(endDate);
                    borrowRepository.save(borrowed);
    
                    book.setAvailability(false); // Update availability to false
                    bookRepository.save(book); // Save the updated book entity
    
                } else {
                    throw new IllegalArgumentException("Book is currently borrowed");
                }
            } else {
                throw new IllegalArgumentException("Book isn't available now");
            }
        }
    }
    
    public List<Borrow> getborrowedBooksByUserID(Long userID) 
    {
        return borrowRepository.findByUserID(userID);
    }

}
