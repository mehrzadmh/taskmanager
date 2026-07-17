package com.mehrzad.taskmanager.controller;

import com.mehrzad.taskmanager.dto.request.create.UserCreateRequest;
import com.mehrzad.taskmanager.dto.response.UserResponse;
import com.mehrzad.taskmanager.dto.request.update.UserUpdateRequest;
import com.mehrzad.taskmanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid  @RequestBody UserCreateRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.saveUser(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest request) {

        return ResponseEntity.ok(userService.updateUser(id,request));
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return  ResponseEntity.noContent().build();
    }
}

