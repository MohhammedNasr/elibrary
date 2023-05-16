package com.project.elibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.elibrary.dto.BookDTO;
import com.project.elibrary.models.User;

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

    @GetMapping("adminHomePage")
    public ModelAndView getAdminPage() {
        ModelAndView mav = new ModelAndView("admin-home-page.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    //open donating form
    @GetMapping("/donate")
    public String getBookForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "donate-books";
    }
}
