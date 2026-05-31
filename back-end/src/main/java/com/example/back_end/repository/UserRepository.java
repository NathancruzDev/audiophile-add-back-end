package com.example.back_end.repository;

import com.example.back_end.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    boolean existsByEmailAddress(String emailAddress);

    Optional<UserEntity> findByEmailAddress(String emailAddress);

}
