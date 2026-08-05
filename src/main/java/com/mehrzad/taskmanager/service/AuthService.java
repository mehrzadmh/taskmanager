package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.request.auth.LoginRequest;
import com.mehrzad.taskmanager.dto.response.LoginResponse;
import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.exception.InvalidPasswordException;
import com.mehrzad.taskmanager.exception.UserNotFoundException;
import com.mehrzad.taskmanager.mapper.UserMapper;
import com.mehrzad.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class AuthService {


    private final UserRepository userRepository ;

    private final PasswordEncoder passwordEncoder;

    private  final UserMapper userMapper;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public LoginResponse authLogin(LoginRequest request){


        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException());


        if(!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )){
            throw new InvalidPasswordException();
        }

        return userMapper.toLoginResponse(user);
    }
}

