package com.project.elibrary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.project.elibrary.dto.BookDTO;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.BookRepository;
import com.project.elibrary.repositories.UserRepo;

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

    //open donating form
    @GetMapping("/donate")
    public String getBookForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "donate-books";
    }
}
