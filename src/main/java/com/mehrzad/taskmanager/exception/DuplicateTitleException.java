package com.mehrzad.taskmanager.exception;

public class DuplicateTitleException extends RuntimeException{
    public DuplicateTitleException() {
        super("Title already exists For This User.");
    }
}
