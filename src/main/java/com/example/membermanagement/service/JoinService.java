package com.example.membermanagement.service;

import com.example.membermanagement.domain.dto.MemberRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface JoinService {
    boolean joinMember(MemberRequestDto memberRequestDto) throws JsonProcessingException;
}
