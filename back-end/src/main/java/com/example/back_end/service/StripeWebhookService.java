package com.example.back_end.service;

import com.example.back_end.repository.OrderPendingRepository;
import com.example.back_end.repository.PaymentRepository;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

public class StripeWebhookService {
    OrderPendingRepository orderPendingRepository;
    PaymentRepository paymentRepository;

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

            if(eventType.equals("payment_intent.succeeded")){

            }

    }

    public void handlePayment
}
