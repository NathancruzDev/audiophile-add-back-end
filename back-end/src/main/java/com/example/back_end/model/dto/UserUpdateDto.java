package com.example.back_end.model.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(
        @NotNull
        String name,
        String emailAdress,
        String phoneNumber,
        String adress,
        String zipCode,
        String City,
        String Country
) {
}
