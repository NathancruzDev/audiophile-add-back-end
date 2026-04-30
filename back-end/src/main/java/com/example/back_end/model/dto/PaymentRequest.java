package com.example.back_end.model.dto;

public record PaymentRequest(
        Long amount,
        String currency,
        String orderId
) {
}
