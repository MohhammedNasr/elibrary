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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.elibrary.dao.UserDao;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.UserRepo;
import com.project.elibrary.services.AuthService;

@Controller
@RequestMapping("/library")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthService authservice;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("")
    public ModelAndView getSignup() {
        ModelAndView mav = new ModelAndView("sign-up-form.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("save-user")
    public String saveUser(@ModelAttribute User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepo.save(user);
        /*
         * Authentication authentication = new UsernamePasswordAuthenticationToken(
         * user.getEmail(),
         * user.getPassword(),
         * user.getAuthorities());
         * Authentication authenticated = this.authservice.authenticate(authentication);
         * SecurityContextHolder.getContext().setAuthentication(authenticated);
         */
        return "redirect:/library/login";

    }

    @GetMapping("login")
    public ModelAndView getLogin() {
        ModelAndView mav = new ModelAndView("login-form.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("check-user")
    public String checkUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        String email = user.getEmail();
        User userfinder = this.userRepo.findByEmail(email).orNull();
        String pass = user.getPassword();
        if (userfinder == null || !bCryptPasswordEncoder.matches(pass, userfinder.getPassword())) {
            redirectAttributes.addAttribute("error", "true");
            return "redirect:/library/login";
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities());
        Authentication authenticated = this.authservice.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        boolean isAdmin = user.getRole().equals("Admin");
        if (isAdmin) {
            return "redirect:/library/adminHomepage";
        } else {
            return "redirect:/library/homepage";
        }
    }

    @GetMapping("reset-pass")
    public ModelAndView getRestpassform() {
        ModelAndView mav = new ModelAndView("reset-pass.html");
        User newUser = new User();
        mav.addObject("user", newUser);
        return mav;
    }

    @PostMapping("change-pass")
    public String changepass(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        String email = user.getEmail();
        User userfinder = this.userRepo.findByEmail(email).orNull();
        if (userfinder == null) {
            redirectAttributes.addFlashAttribute("error", "This email doesn't exist");
            return "redirect:/library/reset-pass";
        }
        String pass = user.getPassword();
        userfinder.setPassword(bCryptPasswordEncoder.encode(pass));
        this.userRepo.save(userfinder);
        return "redirect:/library/login";

    }

    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> userList = userDao.getAllUsers();
        model.addAttribute("users", userList);
        model.addAttribute("isList", true);
        return "profile.html";
    }

    // for finding a specific user (showing profile page) (can be used by admins)
    @GetMapping("/users/{name}")
    public String getUserByName(@PathVariable String name, Model model) {
        User user = userDao.getUserByName(name);
        model.addAttribute("user", user);
        model.addAttribute("isList", false);
        return "profile.html";
    }

    // showing profile page
    @GetMapping("/profile")
    public String getUserByName(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("isList", false);
        return "profile.html";
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

}
