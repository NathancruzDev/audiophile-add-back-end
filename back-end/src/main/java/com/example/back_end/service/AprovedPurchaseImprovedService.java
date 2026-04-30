package com.example.back_end.service;

import com.example.back_end.repository.PaymentOFFApi;
import org.springframework.stereotype.Service;

@Service
public class AprovedPurchaseImprovedService implements PaymentOFFApi {
    @Override
    public boolean approvePurchase() {
        return true;
    }
}
