package com.example.back_end.model.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserResponde(
        Integer id,
        @NotEmpty(message ="adress is necessary")
        String emailAdress,
        @NotEmpty(message ="password is necessary")
        String password) {
}
