package com.example.back_end.model.dto;

import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        @NotNull
        String name,
        @NotNull
        String emailAdress,
        @NotNull
        String password,
        String phoneNumber,
        String adress,
        String zipCode,
        String City,
        String Country
){
}
