package com.example.back_end.model.dto;

import com.example.back_end.model.entity.OrderStatus;
import com.example.back_end.model.entity.ProductEntity;

import java.util.ArrayList;

public record UserDto(
    Integer id,
    String name,
    String emailAdress,
    String phoneNumber,
    String adress,
    String zipCode,
    String City,
    String Country,
    ArrayList<String> paymentMethods,
    ArrayList<OrderStatus>  lastOrders,
    ArrayList<ProductEntity> cartItens
){
}
