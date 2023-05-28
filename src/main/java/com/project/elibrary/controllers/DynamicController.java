package com.project.elibrary.controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.project.elibrary.dto.BookDTO;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.Borrow;
import com.project.elibrary.models.Cart;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.BookRepository;
import com.project.elibrary.repositories.CartRepository;
import com.project.elibrary.repositories.UserRepo;
import com.project.elibrary.services.BookService;
import com.project.elibrary.services.BorrowService;
import com.project.elibrary.services.CartService;

@Controller
@RequestMapping("/library")
public class DynamicController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private CartService cartService;

    @GetMapping("")
    public ModelAndView getSignup() {
        ModelAndView mav = new ModelAndView("sign-up-form.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView mav = new ModelAndView("login-form.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @GetMapping("reset-pass")
    public ModelAndView getRestPassForm() {
        ModelAndView mav = new ModelAndView("reset-pass.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    // go to home page thats showing books "curse"
    @GetMapping("/homepage")
    public String getHomePage(Model model, HttpSession session) {
        String bookName = "curse"; // Default book name

        String selectedBook = (String) session.getAttribute("selectedBook");
        if (selectedBook != null && !selectedBook.isEmpty()) {
            bookName = selectedBook; // Use the selected book name from the session if available
        }

        bookService.homePageBooks(bookName, model);
        return "homepage";
    }

    // go to admin home page
    @GetMapping("adminHomePage")
    public ModelAndView getAdminPage(@AuthenticationPrincipal User user) {
        ModelAndView mav = new ModelAndView("admin-home-page.html");
        long userCount = userRepo.count();
        long donatedBooksCount = bookRepo.count();
        long cartCount = cartRepo.count();
        String username = user.getUsername();
        String profilePicture = user.getProfilePic();
        // passing registered user count and donated books count
        mav.addObject("userCount", userCount);
        mav.addObject("donatedBooksCount", donatedBooksCount);
        mav.addObject("cartCount", cartCount);
        mav.addObject("username", username);
        mav.addObject("profilePicture", profilePicture);
        return mav;
    }

    // go to search page
    @GetMapping("search")
    public ModelAndView getBooks() {
        ModelAndView mav = new ModelAndView("search.html");
        return mav;
    }

    // showing profile page
    @GetMapping("/profile")
    public String getUserByName(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("isList", false);
        return "profile.html";
    }

    //show cart page
    @GetMapping("/cart")
    public ModelAndView viewCart(@AuthenticationPrincipal User user) {
        Cart cart = cartService.getUserCart(user);
        ModelAndView mav = new ModelAndView("cart");
        mav.addObject("cart", cart);
        return mav;
    }

    // open donating form to donate a book
    @GetMapping("/donate")
    public String getDonateForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "donate-books";
    }

    // list of donated books that shows available approved by admin books for
    // borrowing
    @GetMapping("/donatedbooks")
    public ModelAndView getDonatedBooksList(Model model) {
        List<Book> books = bookService.getReviewedBooks();
        ModelAndView mav = new ModelAndView("donatedBooks");
        mav.addObject("books", books);
        return mav;
    }

    // view list of borrowed books
    @GetMapping("/borrowed")
    public ModelAndView showBooks(@AuthenticationPrincipal User user) {
        Long userID = user.getId();
        List<Borrow> books = borrowService.getBorrowedBooksByUserID(userID);
        ModelAndView mav = new ModelAndView("borrowedbooks");
        mav.addObject("books", books);
        return mav;
    }

}
