package com.project.elibrary.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.elibrary.models.pay;
import com.project.elibrary.services.PaymentService;

@Controller
@RequestMapping("/library")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payments")
    public String getAllPayments(Model model) {
        List<pay> payments = paymentService.findAll();
        model.addAttribute("payments", payments);
        return "payments.html";
    }

    @GetMapping("/paymentDetails")
    public String showAddPaymentForm(Model model) {
        model.addAttribute("payment", new pay());
        return "add-payments-details";
    }

    @PostMapping("/addPayment")
    public void createPayment(@RequestBody pay pay) {
        paymentService.save(pay);
    }

}