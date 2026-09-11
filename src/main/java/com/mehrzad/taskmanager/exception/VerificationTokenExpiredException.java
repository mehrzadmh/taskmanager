package com.mehrzad.taskmanager.exception;

public class VerificationTokenExpiredException extends RuntimeException {

    public VerificationTokenExpiredException() {
        super("Verification token has expired.");
    }
}