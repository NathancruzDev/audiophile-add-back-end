package com.example.back_end.repository;

import com.example.back_end.model.entity.PurchasedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface PurchasedRepository extends JpaRepository<PurchasedEntity,Integer> {
    List<PurchasedEntity> findByUserId(Integer userId);
}
