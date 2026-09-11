package com.mehrzad.taskmanager.service;

import com.mehrzad.taskmanager.dto.request.auth.LoginRequest;
import com.mehrzad.taskmanager.dto.response.LoginResponse;
import com.mehrzad.taskmanager.entity.User;
import com.mehrzad.taskmanager.exception.AccountAlreadyVerifiedException;
import com.mehrzad.taskmanager.exception.AccountNotVerifiedException;
import com.mehrzad.taskmanager.exception.InvalidCredentialsException;
import com.mehrzad.taskmanager.exception.InvalidVerificationTokenException;
import com.mehrzad.taskmanager.exception.UserNotFoundException;
import com.mehrzad.taskmanager.exception.VerificationTokenExpiredException;
import com.mehrzad.taskmanager.mapper.UserMapper;
import com.mehrzad.taskmanager.repository.UserRepository;
import com.mehrzad.taskmanager.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    public AuthService(
            UserRepository userRepository,
            UserMapper userMapper,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            EmailService emailService) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public LoginResponse authLogin(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException();
        }

        if (!user.isEnabled()) {
            throw new AccountNotVerifiedException();
        }

        String token = jwtService.generateToken(user);

        LoginResponse response = userMapper.toLoginResponse(user);

        response.setToken(token);

        return response;
    }

    public void verifyAccount(String token) {

        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(InvalidVerificationTokenException::new);

        if (user.isEnabled()) {
            throw new AccountAlreadyVerifiedException();
        }

        if (user.getVerificationTokenExpireAt().isBefore(LocalDateTime.now())) {
            throw new VerificationTokenExpiredException();
        }

        user.setEnabled(true);
        user.setVerificationToken(null);
        user.setVerificationTokenExpireAt(null);

        userRepository.save(user);

        emailService.sendWelcomeEmail(user.getEmail());
    }
}