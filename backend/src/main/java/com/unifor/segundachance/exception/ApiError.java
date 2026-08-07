package com.unifor.segundachance.exception;

import java.time.LocalDateTime;

public class ApiError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;

    public ApiError() {
    }

    public ApiError(Integer status, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}