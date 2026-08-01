package com.example.back_end.controller;

import com.example.back_end.model.dto.OrderPendingDto;
import com.example.back_end.model.dto.user.*;
import com.example.back_end.model.entity.UserEntity;
import com.example.back_end.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<UserCreateDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserCreateDto createdUser = userService.createUser(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    @PostMapping("/create-many-users")
    public ResponseEntity<Void> createManyUsers(@Valid @RequestBody List<UserCreateDto> userCreateDtoList){
        userService.createManyUsers(userCreateDtoList);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<GetUsersDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(userUpdateDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @GetMapping("/my-requests")
    public ResponseEntity<List<OrderPendingDto>> getRequests(@AuthenticationPrincipal UserEntity loggedUser){
        return userService.getMyUserRequests(loggedUser);
    }


}
