package com.example.back_end.service;

import com.example.back_end.model.dto.CartDto;
import com.example.back_end.model.dto.CartItemResponseDto;
import com.example.back_end.model.entity.CartEntity;
import com.example.back_end.model.entity.CartItemEntity;
import com.example.back_end.model.entity.ProductEntity;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.repository.CartRepository;
import com.example.back_end.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public CartDto getCartByUser(UserEntity userEntity) {
        CartEntity cart = cartRepository.findByUserEntity(userEntity)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setUserEntity(userEntity);
                    return cartRepository.save(newCart);
                });
        return mapToDto(cart);
    }

    @Transactional
    public CartDto addProductCart(UserEntity userEntity, Integer productId) {
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

        CartEntity savedCart = cartRepository.save(cart);
        return mapToDto(savedCart);
    }

    private CartDto mapToDto(CartEntity cart) {
        List<CartItemResponseDto> itemDtos = (cart.getItems() != null) ?
                cart.getItems().stream()
                        .map(item -> new CartItemResponseDto(
                                item.getId(),
                                item.getProduct() != null ? item.getProduct().getId() : null,
                                item.getProduct() != null ? item.getProduct().getName() : "Produto Desconhecido",
                                item.getProduct() != null ? item.getProduct().getPrice() : 0.0,
                                item.getQuantity()
                        ))
                        .collect(Collectors.toList())
                : new ArrayList<>();

        return new CartDto(cart.getId(), itemDtos);
    }
}