package com.example.back_end.model.dto;

public record CreatePaymentResponse(
        String paymentIntentId,
        String clientSecret,
        String status
) {
}
