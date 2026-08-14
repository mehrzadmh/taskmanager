package com.mehrzad.taskmanager.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Async
    public void sendWelcomeEmail(String email) {

        System.out.println("Sending Email...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Email Sent To : " + email);
    }



    @Async
    public void sendUpdateEmail(String email) {

        System.out.println("Sending Email...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Email Sent To : " + email);
    }

}