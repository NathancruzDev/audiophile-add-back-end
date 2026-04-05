package com.example.back_end.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name="product")
public class ProductEntity {

    @Id
    Integer id;
    String name;
    Double price;
    @ManyToOne
    @JoinColumn(name = "cart_entity_id")
    CartEntity cartEntity;

    public ProductEntity(Integer id, String name, Double price, CartEntity cartEntity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cartEntity=cartEntity;
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

    public CartEntity getCartEntity() {
        return cartEntity;
    }

    public void setCartEntity(CartEntity cartEntity) {
        this.cartEntity = cartEntity;
    }
}
