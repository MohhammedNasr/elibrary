package com.project.elibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/library")
public class DynamicController {

    @GetMapping("search")
    public ModelAndView getBooks() {
        ModelAndView mav = new ModelAndView("search.html");
        return mav;
    }
}
