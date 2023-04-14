package com.project.elibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.elibrary.dao.UserDao;
import com.project.elibrary.entity.User;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> userList = userDao.getAllUsers();
        model.addAttribute("users", userList);
        model.addAttribute("isList", true);
        return "profile.html";
    }

    @GetMapping("/users/{name}")
    public String getUserByName(@PathVariable String name, Model model) {
        User user = userDao.getUserByName(name);
        model.addAttribute("user", user);
        model.addAttribute("isList", false);
        return "profile.html";
    }
}

