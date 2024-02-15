package com.example.membermanagement.exception.handler;

import com.example.membermanagement.exception.ErrorCode;
import com.example.membermanagement.exception.error.ErrorResponseDto;
import com.example.membermanagement.exception.JoinException;
import com.example.membermanagement.exception.error.MethodArgumentNotValidResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JoinException.class)
    protected ResponseEntity<?> handleJoinException(JoinException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponseDto responseDto = new ErrorResponseDto(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.valueOf(responseDto.getCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(extractErrorMessages(e), HttpStatus.BAD_REQUEST);
    }

    private List<MethodArgumentNotValidResponseDto> extractErrorMessages(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(MethodArgumentNotValidResponseDto::new)
                .collect(Collectors.toList());
    }
}
