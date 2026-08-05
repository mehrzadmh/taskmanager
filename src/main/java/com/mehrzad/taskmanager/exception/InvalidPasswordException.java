package com.mehrzad.taskmanager.exception;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() {super("Invalid email or password.");}
}
