package com.example.back_end.service;

import com.example.back_end.repository.PaymentOFFApi;

public class AprovedPurchaseImprovedService implements PaymentOFFApi {
    @Override
    public boolean approvePurchase() {
        return true;
    }
}
