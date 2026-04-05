package com.example.back_end.repository;

import com.example.back_end.model.entity.CartEntity;
import com.example.back_end.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity,Integer> {
    Optional<CartEntity> findByUser(UserEntity userEntity);
}
