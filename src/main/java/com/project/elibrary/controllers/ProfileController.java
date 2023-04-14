package com.project.elibrary.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.elibrary.entity.User;
import com.project.elibrary.repository.UserRepository;

@Controller
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile/{id}")
    public String showProfile(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> model.addAttribute("user", u));
        return "profile";
    }
}
