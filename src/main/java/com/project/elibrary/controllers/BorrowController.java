package com.project.elibrary.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.elibrary.models.Book;
import com.project.elibrary.models.User;
import com.project.elibrary.repositories.BookRepository;
import com.project.elibrary.repositories.UserRepo;
import com.project.elibrary.services.BookService;
import com.project.elibrary.services.BorrowService;

@Controller
@RequestMapping("/library")
public class BorrowController 
{
    
  @Autowired
  private BookRepository bookRepository;

 
   
  @Autowired 
  private UserRepo userRepository ; 
   
   @Autowired
   private BookService bookService ; 
   @Autowired
   private BorrowService borrowService ; 


  @GetMapping("/borrow")
  public ModelAndView borrowfromDonatedBooksList(Model model) {
    List<Book> books = bookService.getReviewedBooks();
    ModelAndView mav = new ModelAndView("donatedBooks");
    mav.addObject("books", books);
    return mav;
  }
  @GetMapping("/borrowdetails")
public String showBorrowDetails(Model model, HttpSession session) {
  List<Book> selectedBooks = (List<Book>) session.getAttribute("selectedBooks");
  model.addAttribute("selectedBooks", selectedBooks);
  return "borrowdetails";
}

   @PostMapping("/borrow")
  public String borrowBooks(@RequestParam(value = "bookIds", required = false) 
  List<Long> bookIds, HttpSession session) {
    if (bookIds == null || bookIds.isEmpty()) {
    
      return "redirect:/donatedbooks";
    }

    Long userId = (Long) session.getAttribute("userId");
    if (userId == null) {
      // If the user is not logged in, redirect to the login page
      return "redirect:/login";
    }

    User user = userRepository.findById(userId).orNull(); 
    session.setAttribute("user", user);

    List<Book> books = bookRepository.findAllById(bookIds);
    for (Book book : books) {
      book.setBorrower(user);
      bookRepository.save(book);
    }

    return "redirect:/borrowedhistory";

}

@GetMapping("/borrowedhistory")
public String borrowedHistory(Model model, HttpSession session) {
  Long userId = (Long) session.getAttribute("userId");
  if (userId == null) {
    // If the user is not logged in, redirect to the login page
    return "redirect:/login";
  }
    
  User user = userRepository.findById(userId).orNull(); 
  if (user == null) {
    // If the user is not found, redirect to the login page
    return "redirect:/login";
  }

  List<Book> borrowedBooks = bookRepository.findByBorrowerId(userId);
  model.addAttribute("borrowedBooks", borrowedBooks);
  return "borrowedhistory";
}



}











