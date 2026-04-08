package com.example.back_end.model.entity;

import com.example.back_end.model.dto.ProductDto;
import jakarta.persistence.*;

@Entity
@Table(name="product")
public class ProductEntity {

    @Id
    Integer id;
    String name;
    Double price;
    Integer stock;

    public ProductEntity(Integer id, String name, Double price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProductEntity() {

    }

    public ProductEntity(String name, Double price, Integer stock) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
                this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
