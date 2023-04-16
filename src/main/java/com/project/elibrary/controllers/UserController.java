package com.project.elibrary.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.google.common.base.Optional;
import com.project.elibrary.dao.UserDao;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.UserRepo;

@Controller
@RequestMapping("/library")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("")
    public ModelAndView getSignup() {
        ModelAndView mav = new ModelAndView("sign-up-form.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("save-user")
    public String saveUser(@ModelAttribute User user) {
        this.userRepo.save(user);
        return "redirect:/library/homepage";

    }

    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView mav = new ModelAndView("login-form.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("check-user")
    public String checkUser(@ModelAttribute User user) {

        String email = user.getEmail();
        User userfinder = this.userRepo.findByEmail(email).orNull();
        if (userfinder == null) {

            return "redirect:/library/login";
        }
        String pass = user.getPassword();
        String Password = userfinder.getPassword();
        if (pass.equals(Password)) {
            return "redirect:/library/homepage";
        } else {
            return "redirect:/library/login";
        }
    }

    // for viewing users list (can be used by admins)
    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> userList = userDao.getAllUsers();
        model.addAttribute("users", userList);
        model.addAttribute("isList", true);
        return "profile.html";
    }

    // for finding a specific user (showing profile page)
    @GetMapping("/users/{name}")
    public String getUserByName(@PathVariable String name, Model model) {
        User user = userDao.getUserByName(name);
        model.addAttribute("user", user);
        model.addAttribute("isList", false);
        return "profile.html";
    }

    // for editing/updating user info
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

    @PostMapping("/update-username")
    public String updateUsername(@RequestParam String oldUsername, @RequestParam String newUsername,
            RedirectAttributes redirectAttributes) {
        boolean success = userDao.updateUsername(oldUsername, newUsername);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Your user name has been changed successfully");
            return "redirect:/library/edit-profile/" + newUsername;
        } else {
            return "redirect:/library/edit-profile/" + oldUsername;
        }
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String username, @RequestParam String newPassword,
            RedirectAttributes redirectAttributes) {
        boolean success = userDao.updatePassword(username, newPassword);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Your password has been changed successfully");
        }
        return "redirect:/library/edit-profile/" + username;
    }

    @PostMapping("/update-profile-pic")
    public String updateProfilePic(@RequestParam String username, @RequestParam String profilePic,
            RedirectAttributes redirectAttributes) {
        boolean success = userDao.updateProfilePic(username, profilePic);
        if (success) {
            redirectAttributes.addFlashAttribute("message", "Your profile picture has been updated successfully");
        }
        return "redirect:/library/edit-profile/" + username;
    }

}
