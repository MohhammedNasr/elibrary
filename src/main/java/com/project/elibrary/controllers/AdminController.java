package com.project.elibrary.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.elibrary.dao.UserDao;
import com.project.elibrary.models.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserDao userDao;

    // for viewing all registered users
    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> userList = userDao.getAllUsers();
        model.addAttribute("users", userList);
        model.addAttribute("isList", true);
        return "admin-users-list.html";
    }

    // for finding a specific user
    @GetMapping("/users/{name}")
    public String getUserByName(@PathVariable String name, Model model) {
        User user = userDao.getUserByName(name);
        model.addAttribute("user", user);
        model.addAttribute("isList", false);
        return "profile.html";
    }

    // for editing/updating specific user's info
    @GetMapping("/edit-profile/{name}")
    public String editProfile(@PathVariable String name, Model model) {
        User user = userDao.getUserByName(name);
        if (user != null) {
            model.addAttribute("user", user);
            return "edit-profile";
        } else {
            return "redirect:/";
        }
    }
}
