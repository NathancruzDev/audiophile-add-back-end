package com.example.back_end.model.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDto(
        @NotEmpty(message ="adress is necessary")
        String emailAdress,
        @NotEmpty(message ="password is necessary")
        String password
){
}
