package com.example.back_end.service;

import com.example.back_end.model.entity.OrderStatusEnum;
import com.example.back_end.model.entity.PaymentEntity;
import com.example.back_end.repository.PaymentRepository;
import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StripePaymentService {

    private final StripeClient stripeClient;
    private final PaymentRepository paymentRepository;

    public StripePaymentService(
            @Value("${stripe.test.secret.key}") String secretKey,
            PaymentRepository paymentRepository
    ) {
        this.stripeClient = new StripeClient(secretKey);
        this.paymentRepository = paymentRepository;
    }

    public PaymentIntent createPaymentIntent(
            String orderHash,
            Long amountInCents,
            String currency
    ) {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amountInCents)
                    .setCurrency(currency.toLowerCase())
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build()
                    )
                    .putMetadata("orderHash", orderHash)
                    .build();

            PaymentIntent paymentIntent = stripeClient.v1()
                    .paymentIntents()
                    .create(params);

            PaymentEntity payment = new PaymentEntity();
            payment.setOrderId(Long.valueOf(orderHash));
            payment.setStripePaymentIntentId(paymentIntent.getId());
            payment.setAmountInCents(amountInCents);
            payment.setCurrency(currency.toLowerCase());
            payment.setStatus(OrderStatusEnum.PENDING);
            payment.setCreatedAt(LocalDateTime.now());

            paymentRepository.save(payment);

            return paymentIntent;

        } catch (StripeException e) {
            throw new RuntimeException("Error creating Stripe PaymentIntent.", e);
        }
    }


}