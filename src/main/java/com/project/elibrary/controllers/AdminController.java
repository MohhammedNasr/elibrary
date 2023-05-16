package com.project.elibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.elibrary.models.User;

@Controller
@RequestMapping("/library")
public class AdminController {

    @GetMapping("adminHomepage")
    public ModelAndView getHomePage() {
        ModelAndView mav = new ModelAndView("adminHomepage.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }
}
