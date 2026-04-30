package com.example.back_end.model.entity;

public record CreatePaymentIntentResponse(
        String paymentIntentId,
        String clientSecret,
        String status
) {
}
