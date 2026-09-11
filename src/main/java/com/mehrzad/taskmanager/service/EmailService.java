package com.mehrzad.taskmanager.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendVerificationEmail(String email, String token) {

        System.out.println("Sending Verification Email...");

        String verificationLink =
                "http://localhost:8080/auth/verify?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Task Manager - Verify Your Account");
        message.setText("""
                Welcome to Task Manager!

                Please click the link below to activate your account:

                %s

                If you didn't create this account, you can safely ignore this email.
                """.formatted(verificationLink));

        mailSender.send(message);

        System.out.println("Verification Email Sent To : " + email);
    }

    @Async
    public void sendWelcomeEmail(String email) {

        System.out.println("Sending Welcome Email...");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Welcome to Task Manager");
        message.setText("Welcome! Your account has been created successfully.");

        mailSender.send(message);

        System.out.println("Welcome Email Sent To : " + email);
    }

    @Async
    public void sendUpdateEmail(String email) {

        System.out.println("Sending Update Email...");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Account Updated");
        message.setText("Your account information has been updated successfully.");

        mailSender.send(message);

        System.out.println("Update Email Sent To : " + email);
    }
}