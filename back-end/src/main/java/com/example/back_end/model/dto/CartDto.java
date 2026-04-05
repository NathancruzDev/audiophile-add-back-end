package com.example.back_end.model.dto;

import com.example.back_end.model.entity.ProductEntity;

import java.util.List;

public record CartDto(
         Long userEntityId,
         List<ProductEntity> products
) {
}
