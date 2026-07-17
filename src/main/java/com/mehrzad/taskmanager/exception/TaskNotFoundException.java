package com.mehrzad.taskmanager.exception;

public class TaskNotFoundException extends  RuntimeException{
    public TaskNotFoundException() {super("Task Not Found.");}
}
