package com.example.back_end.repository;

import com.example.back_end.model.entity.PurchasedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedRepository extends JpaRepository<PurchasedEntity,Integer> {
}
