package com.project.elibrary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.elibrary.models.Pay;
import com.project.elibrary.repositories.PaymentRepository;


@Service
public class PayServices {
    @Autowired
    private static PaymentRepository paymentRepository;

    @Transactional
    public static void savePay(Pay pay) {
        paymentRepository.save(pay);
    }
}


