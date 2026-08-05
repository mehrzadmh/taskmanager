package com.mehrzad.taskmanager.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    final private LocalDateTime timestamp;

    final private int status;

    final private String message;

    final private String path;

    final  private String error;



    public ErrorResponse(LocalDateTime timestamp,
                         int status,
                         String message,
                         String path,
                         String error) {

        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
        this.error = error;

    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
    public String getError() {
        return error;
    }


}