package com.example.back_end.model.dto.product;

public record ProductDto(
        String name,
        Double price,
        Integer stock
) {
}
