package com.mehrzad.taskmanager.exception;

public class AccountNotVerifiedException extends RuntimeException {

    public AccountNotVerifiedException() {
        super("Please verify your email before logging in.");
    }
}