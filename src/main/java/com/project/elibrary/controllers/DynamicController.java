package com.project.elibrary.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.project.elibrary.dto.BookDTO;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.Borrow;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.BookRepository;
import com.project.elibrary.repositories.UserRepo;
import com.project.elibrary.services.BookService;
import com.project.elibrary.services.BorrowService;

@Controller
@RequestMapping("/library")
public class DynamicController {

    @GetMapping("search")
    public ModelAndView getBooks() {
        ModelAndView mav = new ModelAndView("search.html");
        return mav;
    }

    @GetMapping("homepage")
    public ModelAndView getHomePage() {
        ModelAndView mav = new ModelAndView("homepage.html");
        return mav;
    }

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepository bookRepo;

    @GetMapping("adminHomePage")
    public ModelAndView getAdminPage(@AuthenticationPrincipal User user) {
        ModelAndView mav = new ModelAndView("admin-home-page.html");
        long userCount = userRepo.count();
        long donatedBooksCount = bookRepo.count();
        String username = user.getUsername();
        String profilePicture = user.getProfilePic();
        //passing registered user count and donated books count
        mav.addObject("userCount", userCount);
        mav.addObject("donatedBooksCount", donatedBooksCount);
        mav.addObject("username", username);
        mav.addObject("profilePicture", profilePicture);
        return mav;
    }

    //open donating form to donate a book
    @GetMapping("/donate")
    public String getDonateForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "donate-books";
    }

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    //list of donated books that shows available approved by admin books for borrowing
    @GetMapping("/donatedbooks")
    public ModelAndView getDonatedBooksList(Model model) {
        List<Book> books = bookService.getReviewedBooks();
        ModelAndView mav = new ModelAndView("donatedBooks");
        mav.addObject("books", books);
        return mav;
    }
   @PostMapping("/borrow")
    public String confirmBorrow(@RequestParam("bookId") Long bookId, Model model) {
        // Retrieve the book details based on the bookId
        Book book = bookService.getBookById(bookId);
        // Pass the book details to the "Confirm Borrow" page
        model.addAttribute("book", book);
        return "borrow";
    }

    //view list of borrowed books
    @GetMapping("/borrowed")
    public ModelAndView showBooks(@AuthenticationPrincipal User user) {
        Long userID = user.getId();
        List<Borrow> books = borrowService.getBorrowedBooksByUserID(userID);
        ModelAndView mav = new ModelAndView("borrowedbooks");
        mav.addObject("books", books);
        return mav;
    }
}
