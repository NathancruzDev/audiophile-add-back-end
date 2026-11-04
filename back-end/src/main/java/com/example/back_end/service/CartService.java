package com.example.back_end.service;

import com.example.back_end.model.entity.CartEntity;
import com.example.back_end.model.entity.CartItemEntity;
import com.example.back_end.model.entity.ProductEntity;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.repository.CartRepository;
import com.example.back_end.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public CartEntity addProductCart(UserEntity userEntity, Integer productId){
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found ID: " + productId));

        CartEntity cart = cartRepository.findByUserEntity(userEntity)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setUserEntity(userEntity);
                    return cartRepository.save(newCart);
                });

        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cart.getItems().add(cartItem);
        return cartRepository.save(cart);
    }
}