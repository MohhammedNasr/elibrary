package com.project.elibrary.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.Borrow;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.BookRepository;
import com.project.elibrary.repositories.BorrowRepository;
import com.project.elibrary.repositories.UserRepo;

@Service
public class BorrowService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private UserRepo userRepo;

    public List<Borrow> getAllBorrowedBooks() {
        return borrowRepository.findAll();
    }

    public void saveBorrow(Long bookId, User user, LocalDate startDate, LocalDate endDate) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            if (book.getAvailability()) {
                Borrow borrowed = new Borrow();
                borrowed.setBook(book);
                borrowed.setStartDate(startDate);
                borrowed.setEndDate(endDate);
                borrowed.setUser(user);
                borrowRepository.save(borrowed);
                
                book.setAvailability(false); // Update availability to false
                bookRepository.save(book); // Save the updated book entity
            } else {
                throw new IllegalArgumentException("Book is currently borrowed");
            }
        }
    }

    //sets book availability to true
    public void resetBorrow(Long userID) {
        User user = userRepo.findById(userID).orNull();
        if (user != null) {
            Set<Borrow> borrows = user.getBorrow();
            for (Borrow borrow : borrows) {
                Book book = borrow.getBook();
                book.setAvailability(true);
                bookRepository.save(book);
            }
        }
    }
    
    public List<Borrow> getBorrowedBooksByUserID(Long userID) {
        return borrowRepository.findByUser_Id(userID);
    }

}
