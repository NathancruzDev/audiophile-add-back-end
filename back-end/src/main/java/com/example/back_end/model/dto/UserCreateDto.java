package com.example.back_end.model.dto;

public record UserCreateDto(
        String name,
        String emailAdress,
        String phoneNumber,
        String adress,
        String zipCode,
        String City,
        String Country
){
}
