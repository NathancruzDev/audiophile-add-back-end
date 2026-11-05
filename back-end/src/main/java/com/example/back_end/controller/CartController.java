package com.example.back_end.controller;

import com.example.back_end.model.dto.CartDto;
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

    @GetMapping
    public ResponseEntity<CartDto> getCart(@AuthenticationPrincipal UserEntity userEntity) {
        CartDto cartDto = cartService.getCartByUser(userEntity);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<CartDto> addProductToCart(
            @AuthenticationPrincipal UserEntity userEntity,
            @PathVariable Integer productId) {

        CartDto updatedCart = cartService.addProductCart(userEntity, productId);
        return ResponseEntity.ok(updatedCart);
    }
}