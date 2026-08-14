package com.mehrzad.taskmanager.exception;

public class UserNotAllowToDelete extends  RuntimeException{
    public  UserNotAllowToDelete(){
        super("User Have Dependencies.");
    }
}

