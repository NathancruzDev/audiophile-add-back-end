package com.example.back_end.service;

import com.example.back_end.config.TokenConfig;
import com.example.back_end.exceptions.CartNullException;
import com.example.back_end.model.dto.*;
import com.example.back_end.model.entity.CartEntity;
import com.example.back_end.model.entity.ProductEntity;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.repository.CartRepository;
import com.example.back_end.repository.ProductRepository;
import com.example.back_end.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private TokenConfig tokenConfig;
    CartRepository cartRepository;
    ProductRepository productRepository;

    @Transactional
    public ResponseEntity<UserCreateDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto){
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
    public ResponseEntity<UserLoginDto> login(@Valid @RequestBody UserLoginDto userLogin) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.emailAdress(), userLogin.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        UserEntity user = (UserEntity) authentication.getPrincipal();
        String tokenJWT = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new UserLoginDto(user.getEmailAddress(), tokenJWT));
    }
    @Transactional
    public void addItem(ProductEntity product,Integer id){
        UserEntity user=userRepository.findById(id).orElseThrow(()-> new RuntimeException("This user not exists"));
        CartEntity cartEntity=user.getCartEntity();
            if(cartEntity == null){
                cartEntity=new CartEntity();
                cartEntity.setUser(user);
            }
        ProductEntity productEntity= (ProductEntity) productRepository.findById(product.getId()).orElseThrow(() -> new RuntimeException("This product not exists."));
        cartEntity.addItem(productEntity);
        cartRepository.save(cartEntity);

    }

    @Transactional
    public List<ProductEntity> getCart(Integer userId){
        UserEntity user=userRepository.findById(userId).orElseThrow(()-> new RuntimeException("This user does not exist."));
        CartEntity cartEntity=cartRepository.findByUser(user).orElseThrow(()-> new CartNullException("This cart is null"));
        return cartEntity.getProducts();
    }

    //fazer o cleanCart e fazer o retirar unico item do carrinho

    @Transactional
    public void removeItem(ProductEntity product, Integer id){
        UserEntity userEntity=userRepository.findById(id).orElseThrow(() -> new RuntimeException("This user dont exist."));
        CartEntity cartEntity=cartRepository.findByUser(userEntity).orElseThrow(() -> new RuntimeException("This cart is null"));
        cartEntity.removeItem(product);
        cartRepository.save(cartEntity);
        userRepository.save(userEntity);

    }


}
