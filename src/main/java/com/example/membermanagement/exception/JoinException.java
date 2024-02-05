package com.example.membermanagement.exception;

public class JoinException extends RuntimeException {
    private final ErrorCode errorCode;

    public JoinException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() { return errorCode; }
}
