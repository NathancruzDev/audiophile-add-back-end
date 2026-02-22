package com.example.back_end.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProductEntity {

    @Id
    Integer id;
    String name;
    Double Price;

    public ProductEntity(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        Price = price;
    }

    public ProductEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
