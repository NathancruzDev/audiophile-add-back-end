package com.example.back_end.service;

import com.example.back_end.model.entity.OrderStatusEnum;
import com.example.back_end.model.entity.PaymentEntity;
import com.example.back_end.repository.OrderPendingRepository;
import com.example.back_end.repository.PaymentRepository;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;



@Service
public class StripeWebhookService {
    OrderPendingRepository orderPendingRepository;
    PaymentRepository paymentRepository;
    PurchasedService purchasedService;

    @Value("${stripe.webhook.secret}")
    private String webhookSecret;

    public StripeWebhookService(OrderPendingRepository orderPendingRepository, PaymentRepository paymentRepository) {
        this.orderPendingRepository = orderPendingRepository;
        this.paymentRepository = paymentRepository;
    }

    public void handleWebhook(String payload, String sigHeader){
        Event event;

            try{
                event= Webhook.constructEvent(payload,sigHeader,webhookSecret);
            }catch (Exception e){
                throw new RuntimeException("Invalid webhook signature from stripe api");
            }
        String eventType=event.getType();

        switch (event.getType()) {
            case "payment_intent.succeeded" -> handlePaymentSucceeded(event);
            case "payment_intent.payment_failed" -> handlePaymentFailed(event);
            case "payment_intent.canceled" -> handlePaymentCanceled(event);
            default -> { }
        }

    }

    private void handlePaymentSucceeded(Event event) {
        PaymentIntent paymentIntent = extractPaymentIntent(event);

        PaymentEntity payment = paymentRepository
                .findByStripePaymentIntentId(paymentIntent.getId())
                .orElseThrow(() -> new RuntimeException("Payment not found."));

        payment.setStatus(OrderStatusEnum.PAID);
        paymentRepository.save(payment);

        purchasedService.approveOrdersAfterPayment(payment.getHashCode());
    }

    private void handlePaymentFailed(Event event) {
        PaymentIntent paymentIntent = extractPaymentIntent(event);

        PaymentEntity payment = paymentRepository
                .findByStripePaymentIntentId(paymentIntent.getId())
                .orElseThrow(() -> new RuntimeException("Payment not found."));

        payment.setStatus(OrderStatusEnum.CANCELED);
        paymentRepository.save(payment);

        purchasedService.failOrdersAfterPayment(payment.getHashCode());
    }

    private void handlePaymentCanceled(Event event) {
        PaymentIntent paymentIntent = extractPaymentIntent(event);

        PaymentEntity payment = paymentRepository
                .findByStripePaymentIntentId(paymentIntent.getId())
                .orElseThrow(() -> new RuntimeException("Payment not found."));

        payment.setStatus(OrderStatusEnum.CANCELED);
        paymentRepository.save(payment);

        purchasedService.failOrdersAfterPayment(payment.getHashCode());
    }

    private PaymentIntent extractPaymentIntent(Event event) {
        return (PaymentIntent) event.getDataObjectDeserializer()
                .getObject()
                .orElseThrow(() -> new RuntimeException("Could not deserialize PaymentIntent."));
    }
}
