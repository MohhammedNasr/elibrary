package com.project.elibrary.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.project.elibrary.dao.UserDao;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.Borrow;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.UserRepo;
import com.project.elibrary.services.BookService;
import com.project.elibrary.services.BorrowService;
import com.project.elibrary.services.CartService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartService cartService; 

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    // for viewing all registered users
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> userList = userDao.getAllUsers();
        model.addAttribute("users", userList);
        model.addAttribute("isList", true);
        return "admin-users-list.html";
    }

    //admin add user page
    @GetMapping("/add-user")
    public ModelAndView getAdduser() {
        ModelAndView mav = new ModelAndView("admin-add-user.html");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    //adding user
    @PostMapping("save-user")
    public String saveUserAdmin(@ModelAttribute User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepo.save(user);
        cartService.createCartForUser(user);//creates carts for new users 
        return "redirect:/admin/users";
    }

    //admin add book page
    @GetMapping("/add-book")
    public ModelAndView getAddbook() {
        ModelAndView mav = new ModelAndView("admin-add-book.html");
        Book book = new Book();
        mav.addObject("book", book);
        return mav;
    }

    //adding book
    @PostMapping("/added")
    public String createBook(Book book, @AuthenticationPrincipal User user) {
        book.setAvailability(true); // When any book gets added, it is set to be available
        book.setReviewed(false); // When any book gets added, it is set to be not reviewed and waiting for admin review
        bookService.createBook(book.getTitle(), book.getDescription(), book.getAuthors(), book.getThumbnailUrl(),
                book.getAvailability(), book.getReviewed(), user);
        return "redirect:/admin/allbooks";
    }

    //admin edit user
    @PostMapping("/edit/{id}")
    public ResponseEntity<String> editUserDetails(@PathVariable("id") Long id,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "profilePic") String profilePic,
            @RequestParam(value = "role") String role) {
        userDao.adminEditUser(id, username, profilePic, role);
        return ResponseEntity.ok("User details updated successfully.");
    }

    //admin delete user
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") Long id, @AuthenticationPrincipal User loggedUser) {
        if (loggedUser.getId().equals(id)) {
            return ResponseEntity.badRequest().body("You are not allowed to delete your own account.");
        }
        User user = userDao.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userDao.adminRemoveUser(id);
        return ResponseEntity.ok("User details updated successfully.");
    }

    //boooooks

    //view list of uploaded books/donated books
    @GetMapping("/allbooks")
    public ModelAndView showBooks() {
        List<Book> books = bookService.getAllBooks();
        List<Borrow> borrowedBooks = borrowService.getAllBorrowedBooks();
        ModelAndView mav = new ModelAndView("admin-donatedBooks-list");
        mav.addObject("books", books);
        mav.addObject("borrowedBooks", borrowedBooks);
        return mav;
    }

    // edit book
    @PostMapping("/editBook/{bookId}")
    public String editBook(@PathVariable("bookId") Long bookId,
            @RequestParam("thumbnail") String thumbnail,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            Model model) {
        boolean bookUpdated = bookService.adminEditBook(bookId, thumbnail, title, description);
        if (bookUpdated) {
            model.addAttribute("message", "Book updated successfully.");
        } else {
            model.addAttribute("error", "Failed to update the book.");
        }
        return "redirect:/admin/allbooks";
    }

    // delete book
    @DeleteMapping("/removeBook/{bookId}")
    public ResponseEntity<String> removeBook(@PathVariable("bookId") Long bookId) {
        boolean bookDeleted = bookService.adminDeleteBook(bookId);
        if (bookDeleted) {
            return ResponseEntity.ok("Book deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete book.");
        }
    }

    // accept donated book
    @GetMapping("/acceptBook/{bookID}")
    public String acceptBook(@PathVariable("bookID") Long bookID) {
        bookService.acceptBook(bookID);
        return "redirect:/admin/allbooks";
    }

    // reject donated book
    @GetMapping("/rejectBook/{bookID}")
    public String rejectBook(@PathVariable("bookID") Long bookID) {
        bookService.rejectBook(bookID);
        return "redirect:/admin/allbooks";
    }

    
    
    


    // // for finding a specific user
    // @GetMapping("/users/{name}")
    // public String getUserByName(@PathVariable String name, Model model) {
    //     User user = userDao.getUserByName(name);
    //     model.addAttribute("user", user);
    //     model.addAttribute("isList", false);
    //     return "profile.html";
    // }

     // // for editing/updating specific user's info
    // @GetMapping("/edit-profile/{name}")
    // public String editProfile(@PathVariable String name, Model model) {
    //     User user = userDao.getUserByName(name);
    //     if (user != null) {
    //         model.addAttribute("user", user);
    //         return "edit-profile";
    //     } else {
    //         return "redirect:/";
    //     }
    // }


}
