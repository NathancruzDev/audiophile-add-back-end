package com.example.back_end.model.dto.product;

public record ProductPurchasedDto(
        Integer id,
        String name,
        Double price,
        Integer quantity
) {
}
