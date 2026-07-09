package com.mehrzad.taskmanager.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ValidationErrorResponse {

    private final LocalDateTime timestamp;

    private final int status;

    private final List<String> errors;

    public ValidationErrorResponse(LocalDateTime timestamp,
                                   int status,
                                   List<String> errors) {

        this.timestamp = timestamp;
        this.status = status;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }
}