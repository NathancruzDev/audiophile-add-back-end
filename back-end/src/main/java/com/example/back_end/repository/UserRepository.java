package com.example.back_end.repository;

import com.example.back_end.model.dto.UserDto;
import com.example.back_end.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    boolean existsByEmailAdress(String emailAdress);


}
