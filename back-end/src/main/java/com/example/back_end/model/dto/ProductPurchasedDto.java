package com.example.back_end.model.dto;

public record ProductPurchasedDto(
        Integer id,
        String name,
        Double price,
        Integer stock
) {
}
