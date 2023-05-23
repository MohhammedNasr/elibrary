package com.project.elibrary.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    
    public List<Borrow> getAllBorrowedBooks() {
        return borrowRepository.findAll();
    }

    public void saveBorrow(Long bookId, Long userID, LocalDate startDate, LocalDate endDate) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            
            // Get the book details
            String bookName = book.getTitle();
            String description = book.getDescription();
            String image = book.getThumbnailUrl();
            List<String> authors = book.getAuthors();
    
            if (book.getAvailability()) {
                Borrow borrowed = new Borrow();
                borrowed.setBookId(bookId);
                borrowed.setBookName(bookName);
                borrowed.setAuthors(authors);
                borrowed.setDescription(description);
                borrowed.setImage(image);
                borrowed.setUserID(userID);
                borrowed.setStartDate(startDate);
                borrowed.setEndDate(endDate);
                borrowRepository.save(borrowed);
                
                book.setAvailability(false); // Update availability to false
                bookRepository.save(book); // Save the updated book entity
            } else {
                throw new IllegalArgumentException("Book is currently borrowed");
            }
        }
    }
    
    public List<Borrow> getborrowedBooksByUserID(Long userID) {
        return borrowRepository.findByUserID(userID);
    }

}
