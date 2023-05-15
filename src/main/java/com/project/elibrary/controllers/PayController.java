package com.project.elibrary.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.elibrary.models.Pay;
import com.project.elibrary.repositories.PaymentRepository;
import com.project.elibrary.services.PayServices;

@Controller
@RequestMapping("/payment")
public class PayController 
{
    @Autowired
    private PaymentRepository paymentRepository ; 

    @Autowired
    private PayServices payServices ; 
    
    @GetMapping("pay")
    public ModelAndView showPaymentForm(Model model) {
       ModelAndView mav=  new ModelAndView("Pay.html"); 
        return  mav; 

    }
    @PostMapping("/submit-payment")
    public String submitPayment(@ModelAttribute Pay pay) {
        if (pay.getPaymentMethod().equals("payment-via-visa")) {
            return "redirect:/PayVisa.html "; 
        } else {
            PayServices.savePay(pay); 
            return "payment-confirmation.html"; 
        }
    }

    @GetMapping("/Visa-payment")
    public String showCardPaymentPage() {
 
        return "PayVisa.html";
    }
}



