package com.example.back_end.model.dto;

import java.math.BigDecimal;

public record CartItemResponseDto(
        Integer id,
        Integer productId,
        String name,
        Double price,
        Integer quantity
) {}