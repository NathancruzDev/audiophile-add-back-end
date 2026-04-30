package com.example.back_end.model.dto;

public record CheckoutResponseDto (
        String orderHash,
        String paymentIntentId,
        String clientSecret,
        Long amountInCents
){
}
