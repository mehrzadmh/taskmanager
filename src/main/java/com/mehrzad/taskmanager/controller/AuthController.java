package com.mehrzad.taskmanager.controller;


import com.mehrzad.taskmanager.dto.request.auth.LoginRequest;
import com.mehrzad.taskmanager.dto.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {


        return ResponseEntity.ok(new LoginResponse());
    }
}
