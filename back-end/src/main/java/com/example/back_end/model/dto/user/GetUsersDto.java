package com.example.back_end.model.dto.user;

public record GetUsersDto(
        Integer id,
        String name,
        String emailAdress,
        String phoneNUmber
) {
}
