package com.example.springbootdemo;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable {
    private int code;
    private String message;
    @Nullable
    private T data;

    public BaseResponse(int code, String message, @Nullable T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }
}

