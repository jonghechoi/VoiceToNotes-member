package com.example.membermanagement.service;

import com.example.membermanagement.domain.dto.MemberRequestDto;

public interface JoinService {
    boolean joinMember(MemberRequestDto memberRequestDto);
}
