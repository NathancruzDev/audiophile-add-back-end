package com.example.back_end.service;

import com.example.back_end.repository.OrderPendingRepository;
import com.example.back_end.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    OrderPendingRepository orderPendingRepository;
    PaymentRepository paymentRepository;

    public PaymentService(OrderPendingRepository orderPendingRepository, PaymentRepository paymentRepository) {
        this.orderPendingRepository = orderPendingRepository;
        this.paymentRepository = paymentRepository;
    }
}
