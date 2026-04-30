package com.example.back_end.repository;

import com.example.back_end.model.entity.OrderPendingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderPendingRepository extends JpaRepository<OrderPendingEntity,Integer> {
    List<OrderPendingEntity> findByPurchasedHashCode(String purchasedHashCode);
}
