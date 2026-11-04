package com.example.back_end.controller;

import com.example.back_end.model.entity.CartEntity;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/add/{productId}")
    public ResponseEntity<CartEntity> addProductToCart(
            @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable Integer productId) {

        CartEntity updatedCart = cartService.addProductCart(userEntity, productId);
        return ResponseEntity.ok(updatedCart);
    }
}