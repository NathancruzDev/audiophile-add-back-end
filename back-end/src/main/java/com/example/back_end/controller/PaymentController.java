package com.example.back_end.controller;


import com.example.back_end.model.dto.CheckoutRequestDto;
import com.example.back_end.model.dto.CheckoutResponseDto;
import com.example.back_end.service.PurchasedService;
import com.example.back_end.service.StripePaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final StripePaymentService stripePaymentService;
    private final PurchasedService purchasedService;

    public PaymentController(StripePaymentService stripePaymentService, PurchasedService purchasedService) {
        this.stripePaymentService = stripePaymentService;
        this.purchasedService = purchasedService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponseDto> createCheckout(@RequestBody CheckoutRequestDto request) throws StripeException {
        CheckoutResponseDto responseDto= purchasedService.checkout(request.products(),request.usersDto(),request.formPayment());
        return ResponseEntity.ok(responseDto);
    }

}
