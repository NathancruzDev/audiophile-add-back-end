package com.example.back_end.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private UserEntity userEntity;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ProductEntity> items = new ArrayList<>();

    public CartEntity(Integer id, UserEntity userEntity, List<ProductEntity> items) {
        this.id = id;
        this.userEntity = userEntity;
        this.items = items;
    }

    public CartEntity() {
    }

    public Integer getId() {
        return id;
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
