package com.example.membermanagement.service;

import com.example.membermanagement.domain.dto.UserRequestDto;

public interface JoinService {
    boolean joinUser(UserRequestDto userRequestDto);
}
