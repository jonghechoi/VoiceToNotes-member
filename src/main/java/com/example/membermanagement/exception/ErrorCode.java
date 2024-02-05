package com.example.membermanagement.exception;

public interface ErrorCode {
    int getStatus();
    String getCode();
    String getMessage();
}
