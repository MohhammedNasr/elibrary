package com.project.elibrary.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.elibrary.models.User;
import com.project.elibrary.services.BorrowService;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
    @Autowired
    private BorrowService borrowService;

    @PostMapping("/add")
    public String saveFavorite(
            @RequestParam(value = "bookId") Long bookId,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes) {

        //Long userID = user.getId();
        LocalDate startDate = LocalDate.now();

        borrowService.saveBorrow(bookId, user, startDate, endDate);

        // Add a redirect attribute to the response
        redirectAttributes.addFlashAttribute("message", "Borrow saved successfully.");

        // Redirect to "/borrow/borrowed/"
        return "redirect:/library/borrowed/";
    }
}
