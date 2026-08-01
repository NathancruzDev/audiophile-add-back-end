package com.example.back_end.model.dto.user;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(
        @NotNull
        String name,
        String emailAdress,
        String phoneNumber,
        String zipCode,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state
) {
}
