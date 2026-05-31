package com.example.back_end.model.dto;

import com.example.back_end.model.dto.product.ProductPurchasedDto;
import com.example.back_end.model.dto.user.GetUsersDto;
import com.example.back_end.model.entity.FormPayment;

import java.util.List;

public record CheckoutRequestDto(
        List<ProductPurchasedDto> products,
        GetUsersDto usersDto
        ,FormPayment formPayment
){
}
