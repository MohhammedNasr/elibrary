package com.project.elibrary.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.elibrary.dao.UserDao;
import com.project.elibrary.models.Book;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.UserRepo;
import com.project.elibrary.services.AuthService;
import com.project.elibrary.services.BookService;
import com.project.elibrary.services.CartService;

@Controller
@RequestMapping("/library")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthService authService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CartService cartService; 

    @Autowired
    private BookService bookService;

    @Autowired
    private UserDao userDao;


    @PostMapping("save-user")
    public String saveUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        String email = user.getEmail();
        User userFinder = this.userRepo.findByEmail(email).orNull();
        if (userFinder != null) {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/library"; }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       this.userRepo.save(user);
        cartService.createCartForUser(user);//creates carts for new users 
        return "redirect:/library/login";
    }

    @PostMapping("check-user")
    public String checkUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        String email = user.getEmail();
        User userFinder = this.userRepo.findByEmail(email).orNull();
        String pass = user.getPassword();
        if (userFinder == null || !bCryptPasswordEncoder.matches(pass, userFinder.getPassword())) {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/library/login";
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
        Authentication authenticated = this.authService.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        boolean isAdmin = user.getRole().equals("Admin");
        if (isAdmin) {
            return "redirect:/library/adminHomepage";
        } else {
            return "redirect:/library/homepage";
        }
    }

    @PostMapping("change-pass")
    public String changePass(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        String email = user.getEmail();
        User userFinder = this.userRepo.findByEmail(email).orNull();
        if (userFinder == null) {
            redirectAttributes.addFlashAttribute("error", "This email doesn't exist");
            return "redirect:/library/reset-pass";
        }
        String pass = user.getPassword();
        userFinder.setPassword(bCryptPasswordEncoder.encode(pass));
        this.userRepo.save(userFinder);
        return "redirect:/library/login";

    }

    // for editing/updating user info
    @GetMapping("/edit-profile")
    public String editProfile(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-profile";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/update-username")
    public String updateUsername(@RequestParam String oldUsername, @RequestParam String newUsername,
            RedirectAttributes redirectAttributes) {
        boolean success = userDao.updateUsername(oldUsername, newUsername);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Your user name has been changed successfully");
            User user = userDao.getUserByName(newUsername);
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                    user,
                    user.getPassword(),
                    user.getAuthorities());
            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
            return "redirect:/library/edit-profile";
        } else {
            return "redirect:/library/edit-profile";
        }
    }

    @PostMapping("/update-password")
    public String updatePassword(@AuthenticationPrincipal User user, @RequestParam String newPassword,
            RedirectAttributes redirectAttributes) {
        Long userID = user.getId();
        boolean success = userDao.updatePassword(userID, newPassword);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Your password has been changed successfully");
        }
        return "redirect:/library/edit-profile";
    }

    @PostMapping("/update-profile-pic")
    public String updateProfilePic(@AuthenticationPrincipal User user, @RequestParam String profilePic,
            RedirectAttributes redirectAttributes) {
        Long userID = user.getId();
        boolean success = userDao.updateProfilePic(userID, profilePic);
        if (success) {
            User updatedUser = userDao.getUserById(userID);
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                    updatedUser,
                    updatedUser.getPassword(),
                    updatedUser.getAuthorities());
            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
            redirectAttributes.addFlashAttribute("message", "Your profile picture has been updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to update your profile picture.");
        }
        return "redirect:/library/edit-profile";
    }

    // view list of donated books by userID
    @GetMapping("/donated")
    public ModelAndView showDonatedBooks(@AuthenticationPrincipal User user) {
        Long userID = user.getId();
        List<Book> books = bookService.getBooksByUserID(userID);
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

}
