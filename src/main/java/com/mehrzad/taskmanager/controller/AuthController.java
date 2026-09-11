package com.mehrzad.taskmanager.controller;


import com.mehrzad.taskmanager.dto.request.auth.LoginRequest;
import com.mehrzad.taskmanager.dto.response.LoginResponse;
import com.mehrzad.taskmanager.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Auth Management", description = "Auth Management APIs")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private  final AuthService authService ;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @Operation(summary = "login User Request")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {


        return ResponseEntity.ok(authService.authLogin(request));
    }

    @Operation(summary = "Verify user account")
    @GetMapping("/verify")
    public ResponseEntity<String> verifyAccount(
            @RequestParam String token) {

        authService.verifyAccount(token);

        return ResponseEntity.ok("Your account has been successfully verified.");
    }
}
