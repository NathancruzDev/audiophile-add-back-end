package com.example.back_end.model.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


public class OrderStatus {

    UserEntity userEntity;
    UUID id;
    BigDecimal values;
    Integer Hashcode;
    LocalDateTime createDate;

    public OrderStatus(UserEntity userEntity, UUID id, BigDecimal values, Integer hashcode, LocalDateTime createDate) {
        this.userEntity = userEntity;
        this.id = id;
        this.values = values;
        Hashcode = hashcode;
        this.createDate = createDate;
    }

    public OrderStatus() {
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getValues() {
        return values;
    }

    public void setValues(BigDecimal values) {
        this.values = values;
    }

    public Integer getHashcode() {
        return Hashcode;
    }

    public void setHashcode(Integer hashcode) {
        Hashcode = hashcode;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
