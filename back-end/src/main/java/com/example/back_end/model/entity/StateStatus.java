package com.example.back_end.model.entity;

public enum StateStatus {
    CREATED,
    PENDING_PAYMENT,
    PAID,
    PROCESSING,
    SHIPPED,
    DELIVERED,

    CANCELLED,
    REFUNDED,

    FAILED
}
