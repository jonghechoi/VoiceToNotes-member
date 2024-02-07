package com.example.membermanagement.exception.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode implements com.example.membermanagement.exception.ErrorCode {
    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    DUPLICATED_USER_NAME(401, "C002", "Already Occupied Value");

    private final int status;
    private final String code;
    private final String message;
}
