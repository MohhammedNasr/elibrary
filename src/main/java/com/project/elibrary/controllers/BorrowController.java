package com.project.elibrary.controllers;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.protobuf.TextFormat.ParseException;
import com.project.elibrary.models.User;
import com.project.elibrary.services.BorrowService;

@Controller
@RequestMapping("/borrow")
public class BorrowController 
{
    @Autowired
    private BorrowService borrowService ; 
    @PostMapping("/add")
    public ResponseEntity<Void> saveFavorite(
           // @RequestParam(value = "name", required = false) String bookName,
            @RequestParam(value = "bookId", required = false) Long bookId,
            //@RequestParam(value = "authors", required = false) String authors,
           // @RequestParam(value = "image", required = false) String image,
           // @RequestParam(value = "description", required = false) String Description ,
            //@RequestParam(value = "endDate", required = false) String endDate,
       
            @AuthenticationPrincipal User user) {
        Long userID = user.getId();
        LocalDate startDate = LocalDate.now();
 
       
       borrowService.saveBorrow(bookId,userID, startDate);
        return ResponseEntity.ok().build();
      
    }
}
