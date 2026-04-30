package com.example.back_end.model.entity;

public enum PaymentStatus {
    CREATED,
    REQUIRES_PAYMENT_METHOD,
    REQUIRES_ACTION,
    PROCESSING,
    SUCCEEDED,
    FAILED
}
