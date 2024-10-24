package com.hatio.todo.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse extends ApiResponse {

    @JsonProperty("data")
    private String message;

    public ErrorResponse(int statusCode, String message) {
        super(false, statusCode); // Call to the parent constructor
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
