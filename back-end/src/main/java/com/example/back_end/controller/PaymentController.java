package com.example.back_end.controller;

import com.example.back_end.model.dto.CheckoutRequestDto;
import com.example.back_end.model.dto.CheckoutResponseDto;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.service.PurchasedService;
import com.stripe.exception.StripeException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PurchasedService purchasedService;

    public PaymentController(PurchasedService purchasedService) {
        this.purchasedService = purchasedService;
    }

    @PostMapping("/checkout")
    public ResponseEntity<CheckoutResponseDto> createCheckout(
            @AuthenticationPrincipal UserEntity loggedUser,
            @RequestBody CheckoutRequestDto request) throws StripeException {

        CheckoutResponseDto responseDto = purchasedService.checkout(
                request.products(),
                loggedUser,
                request.formPayment()
        );
        return ResponseEntity.ok(responseDto);
    }
}