package com.example.back_end.service;

import com.example.back_end.model.dto.GetUsersDto;
import com.example.back_end.model.dto.UserCreateDto;
import com.example.back_end.model.dto.UserDto;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PaymentService {
    UserRepository userRepository;

    public void createUser(@Valid @RequestBody UserCreateDto userCreateDto){
        if(userRepository.existsByEmailAdress(userCreateDto.emailAdress())){
            throw new RuntimeException("The user_id have exist");
        }
        UserEntity userEntity=new UserEntity(userCreateDto);
        userRepository.save(userEntity);
        System.out.println("Usuario criado" + userCreateDto);
    }

    public List<GetUsersDto> getAllUsers(){
        return userRepository.findAll().stream().map(
                userEntity -> new GetUsersDto(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getEmailAdress(),
                        userEntity.getPhoneNumber()
                )
        ).collect(Collectors.toList());
    }


}
