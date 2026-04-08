package com.example.back_end.service;

import com.example.back_end.config.TokenConfig;
import com.example.back_end.model.dto.*;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenConfig tokenConfig;
    ProductRepository productRepository;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenConfig tokenConfig, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
        this.productRepository = productRepository;
    }

    @Transactional
    public ResponseEntity<UserCreateDto> createUser(@Valid UserCreateDto userCreateDto){
        if(userRepository.existsByEmailAdress(userCreateDto.emailAdress())){
            throw new RuntimeException("The user_id have exist");
        }
        UserEntity userEntity=new UserEntity(userCreateDto);
        userRepository.save(userEntity);
        System.out.println("Usuario criado" + userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreateDto);
    }

    public List<GetUsersDto> getAllUsers(){
        return userRepository.findAll().stream().map(
                userEntity -> new GetUsersDto(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getEmailAddress(),
                        userEntity.getPhoneNumber()
                )
        ).collect(Collectors.toList());
    }

    @Transactional
    public ResponseEntity<UserLoginDto> login( UserLoginDto userLogin) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.emailAdress(), userLogin.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String tokenJWT = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new UserLoginDto(user.getEmailAddress(), tokenJWT));
    }

    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto){
        UserEntity userEntity=userRepository.findByEmailAdress(userUpdateDto.emailAdress()).orElseThrow(() -> new RuntimeException("This user don't exists."));
            if(!userEntity.getEmailAddress().equals(userUpdateDto.emailAdress())){
                //troca de email tem que ser tratada mais séria.
                throw new RuntimeException("");
            }

        userEntity.setName(userUpdateDto.name());
        userEntity.setPhoneNumber(userUpdateDto.phoneNumber());
        userEntity.setAddress(userUpdateDto.adress());
        userEntity.setZipCode(userUpdateDto.zipCode());
        userEntity.setCity(userUpdateDto.City());
        userEntity.setCountry(userUpdateDto.Country());

        userRepository.save(userEntity);
    }


}
