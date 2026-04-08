package com.example.back_end.controller;

import com.example.back_end.model.dto.GetUsersDto;
import com.example.back_end.model.dto.UserCreateDto;
import com.example.back_end.model.dto.UserLoginDto;
import com.example.back_end.model.dto.UserUpdateDto;
import com.example.back_end.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @GetMapping
    public String teste(){
        return "aplicação testada";
    }

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserCreateDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return userService.createUser(userCreateDto);
    }

    @GetMapping
    public ResponseEntity<List<GetUsersDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(userUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }
}
