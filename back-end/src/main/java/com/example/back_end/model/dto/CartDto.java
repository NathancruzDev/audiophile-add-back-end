package com.example.back_end.model.dto;

import com.example.back_end.model.entity.ProductEntity;
import com.example.back_end.model.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

    private Integer id;
    private UserEntity userEntity;
    private List<ProductEntity> items = new ArrayList<>();

    public CartDto(Integer id, UserEntity userEntity, List<ProductEntity> items) {
        this.id = id;
        this.userEntity = userEntity;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<ProductEntity> getItems() {
        return items;
    }

    public void setItems(List<ProductEntity> items) {
        this.items = items;
    }
}
