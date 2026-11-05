package com.example.back_end.model.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private Integer id;
    private List<CartItemResponseDto> items = new ArrayList<>();

    public CartDto(Integer id, List<CartItemResponseDto> items) {
        this.id = id;
        this.items = items;
    }

    public CartDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CartItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemResponseDto> items) {
        this.items = items;
    }
}