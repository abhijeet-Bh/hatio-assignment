package com.hatio.todo.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessResponse<T> extends com.hrelix.app.utils.ApiResponse {

    @JsonProperty("data")
    private T data;

    public SuccessResponse(boolean success, int statusCode, T data) {
        super(success, statusCode); // Call to the parent constructor
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
