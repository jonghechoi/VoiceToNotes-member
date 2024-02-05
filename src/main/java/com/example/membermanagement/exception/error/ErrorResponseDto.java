package com.example.membermanagement.exception.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private int status;
    private String code;
    private String message;
}
