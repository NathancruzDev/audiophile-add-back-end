package com.example.back_end.service;

import com.example.back_end.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService {

    private final PaymentRepository paymentRepository;
    private final PurchasedService purchasedService;


    public StripePaymentService(PaymentRepository paymentRepository, PurchasedService purchasedService) {
        this.paymentRepository = paymentRepository;
        this.purchasedService = purchasedService;
    }
}