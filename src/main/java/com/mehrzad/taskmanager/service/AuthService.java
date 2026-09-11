package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.request.auth.LoginRequest;
import com.mehrzad.taskmanager.dto.response.LoginResponse;
import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.exception.UserNotFoundException;
import com.mehrzad.taskmanager.mapper.UserMapper;
import com.mehrzad.taskmanager.repository.UserRepository;
import com.mehrzad.taskmanager.security.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;


    public AuthService(
            UserRepository userRepository,
            UserMapper userMapper,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;

    }

    public LoginResponse authLogin(LoginRequest request) {



        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(UserNotFoundException::new);

        String token = jwtService.generateToken(user);

        LoginResponse response = userMapper.toLoginResponse(user);

        response.setToken(token);

        return response;
    }
}