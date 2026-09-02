package com.example.back_end.controller;

import com.example.back_end.model.dto.user.GetUsersDto;
import com.example.back_end.model.dto.user.UserCreateDto;
import com.example.back_end.model.dto.user.UserDto;
import com.example.back_end.model.dto.user.UserUpdateDto;
import com.example.back_end.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {w
        this.userService = userService;
    }


    @PostMapping("/create-many")
    public ResponseEntity<Void> createManyUsers(@Valid @RequestBody List<UserCreateDto> userCreateDtoList) {
        userService.createManyUsers(userCreateDtoList);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<GetUsersDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserAdmin(@PathVariable Integer id, @Valid @RequestBody UserUpdateDto updateDto) {
        userService.updateUserById(id, updateDto);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/roles")
    public ResponseEntity<Void> updateUserRole(@PathVariable Integer id, @RequestParam String newRole) {
        userService.updateUserRole(id, newRole);
        return ResponseEntity.noContent().build();
    }

}