package com.mehrzad.taskmanager.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super("Email already exists.");
    }

}