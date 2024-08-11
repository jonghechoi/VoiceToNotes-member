package com.example.membermanagement.service.impl;

import com.example.membermanagement.adaptor.MemberManagementProducer;
import com.example.membermanagement.domain.Member;
import com.example.membermanagement.domain.dto.MemberRequestDto;
import com.example.membermanagement.exception.JoinException;
import com.example.membermanagement.exception.enumeration.ErrorCode;
import com.example.membermanagement.repository.MemberRepository;
import com.example.membermanagement.service.JoinService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberManagementProducer memberManagementProducer;

    @Override
    public boolean joinMember(MemberRequestDto memberRequestDto) throws JsonProcessingException {
        memberRepository.findMemberByEmail(memberRequestDto.getEmail())
                .ifPresent(
                        user -> {
                            throw new JoinException(ErrorCode.DUPLICATED_USER_NAME);
                        }
                );

        String bCryptPassword = bCryptPasswordEncoder.encode(memberRequestDto.getPassword());
        memberRequestDto.setPassword(bCryptPassword);
        Member member = MemberRequestDto.toEntity(memberRequestDto);

        memberManagementProducer.createUser(member); // auth 서비스로 비동기 메시지 전송
        Member saveCheck = memberRepository.save(member);

        return Optional.of(saveCheck).isPresent();
    }
}
