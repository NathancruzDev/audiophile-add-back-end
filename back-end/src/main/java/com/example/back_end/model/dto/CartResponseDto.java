package com.example.back_end.model.dto;

import java.util.List;

public record CartResponseDto(
        Integer id,
        List<CartItemResponseDto> items
) {
}
