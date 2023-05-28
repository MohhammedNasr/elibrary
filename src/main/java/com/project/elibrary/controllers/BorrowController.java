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

    //user borrowing a book
    @PostMapping("/add")
    public String saveBorrow(
            @RequestParam(value = "bookId") Long bookId,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @AuthenticationPrincipal User user,
            RedirectAttributes redirectAttributes) {
        LocalDate startDate = LocalDate.now();
        borrowService.saveBorrow(bookId, user, startDate, endDate);
        redirectAttributes.addFlashAttribute("message", "Book borrowed successfully.");

        return "redirect:/library/borrowed/";
    }
}
