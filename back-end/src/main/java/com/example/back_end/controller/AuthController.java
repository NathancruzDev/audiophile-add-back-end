package com.example.back_end.controller;


import com.example.back_end.model.dto.OrderPendingDto;
import com.example.back_end.model.dto.user.UserCreateDto;
import com.example.back_end.model.dto.user.UserLoginDto;
import com.example.back_end.model.dto.user.UserUpdateDto;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updateUser")
    public ResponseEntity<Void> updateUserAdmin(@Valid @RequestBody UserUpdateDto userUpdateDto) {

            if(userService.isUserLogged().equals(true)){
                userService.updateUser(userUpdateDto);
                return ResponseEntity.noContent().build();
            }
            else{
                throw new RuntimeException("The user is not logged in; therefore, they cannot do anything regarding their profile.");
            }

    }
    @PostMapping("/register")
    public ResponseEntity<UserCreateDto> register(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserCreateDto createdUser = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // Autenticação de usuário
    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/my-requests")
    public ResponseEntity<List<OrderPendingDto>> getMyRequests(@AuthenticationPrincipal UserEntity loggedUser) {
        return ResponseEntity.ok(userService.getMyUserRequests(loggedUser));
    }
}
