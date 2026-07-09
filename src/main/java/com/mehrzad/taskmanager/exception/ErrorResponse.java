package com.mehrzad.taskmanager.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    final private LocalDateTime timestamp;

    final private int status;

    final private String message;



    public ErrorResponse(LocalDateTime timestamp,
                         int status,
                         String message) {

        this.timestamp = timestamp;
        this.status = status;
        this.message = message;

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


}