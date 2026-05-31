package com.example.back_end.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    @Column(unique = true)
    private String stripePaymentIntentId;

    private Long amountInCents;

    private String currency;

    private String hashCode;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    private LocalDateTime createdAt;

    public PaymentEntity() {
    }

    public PaymentEntity(Long id, Long orderId, String stripePaymentIntentId, Long amountInCents, String currency, String hashCode, OrderStatusEnum status, LocalDateTime createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.stripePaymentIntentId = stripePaymentIntentId;
        this.amountInCents = amountInCents;
        this.currency = currency;
        this.hashCode = hashCode;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStripePaymentIntentId() {
        return stripePaymentIntentId;
    }

    public void setStripePaymentIntentId(String stripePaymentIntentId) {
        this.stripePaymentIntentId = stripePaymentIntentId;
    }

    public Long getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(Long amountInCents) {
        this.amountInCents = amountInCents;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


}
