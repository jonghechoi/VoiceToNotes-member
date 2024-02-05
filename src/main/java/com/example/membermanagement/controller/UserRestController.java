package com.example.membermanagement.controller;

import com.example.membermanagement.domain.dto.UserRequestDto;
import com.example.membermanagement.exception.JoinException;
import com.example.membermanagement.exception.enumeration.ErrorCode;
import com.example.membermanagement.service.impl.JoinServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final JoinServiceImpl joinService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody UserRequestDto userRequestDto) {
        if(joinService.joinUser(userRequestDto))
            return new ResponseEntity<>(HttpStatus.OK);
        else
            throw new JoinException(ErrorCode.INVALID_INPUT_VALUE);
    }
}
