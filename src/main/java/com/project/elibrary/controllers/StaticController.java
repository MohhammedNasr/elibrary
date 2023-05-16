package com.project.elibrary.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StaticController {
    @GetMapping("HomePage")
    public String getIndex() {
        return "homepage.html";
    }
}
