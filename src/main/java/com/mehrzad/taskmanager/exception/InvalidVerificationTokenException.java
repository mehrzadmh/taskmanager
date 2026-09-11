package com.mehrzad.taskmanager.exception;

public class InvalidVerificationTokenException extends RuntimeException {

    public InvalidVerificationTokenException() {
        super("Invalid verification token.");
    }
}