package com.example.back_end.model.dto.user;

import com.example.back_end.model.entity.OrderStatusEnum;

import java.util.List;

public record UserDto(
        Integer id,
        String name,
        String emailAdress,
        String phoneNumber,
        String zipCode,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        List<String> paymentMethods,
        List<OrderStatusEnum> lastOrders
){

}
