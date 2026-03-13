package com.example.back_end.model.dto;

public record GetUsersDto(
        Integer id,
        String name,
        String emailAdress,
        String phoneNUmber
) {
}
