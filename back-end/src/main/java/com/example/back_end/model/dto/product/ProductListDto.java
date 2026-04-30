package com.example.back_end.model.dto.product;

public record ProductListDto(
        Integer id,
        String name,
        Double price,
        Integer stock
) {
}
