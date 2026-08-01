package com.example.back_end.model.dto.user;

import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        @NotNull
        String name,
        @NotNull
        String emailAdress,
        @NotNull
        String password,
        String phoneNumber,
        String zipCode,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state
){
}
