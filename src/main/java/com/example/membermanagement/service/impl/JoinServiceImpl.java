package com.example.membermanagement.service.impl;

import com.example.membermanagement.domain.Member;
import com.example.membermanagement.domain.dto.MemberRequestDto;
import com.example.membermanagement.exception.JoinException;
import com.example.membermanagement.exception.enumeration.ErrorCode;
import com.example.membermanagement.repository.MemberRepository;
import com.example.membermanagement.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean joinMember(MemberRequestDto memberRequestDto) {
        memberRepository.findMemberByUid(memberRequestDto.getUid())
                .ifPresent(
                        user -> {
                            throw new JoinException(ErrorCode.DUPLICATED_USER_NAME);
                        }
                );

        String bCryptPassword = bCryptPasswordEncoder.encode(memberRequestDto.getPassword());
        memberRequestDto.setPassword(bCryptPassword);
        Member member = MemberRequestDto.toEntity(memberRequestDto);

        Member saveCheck = memberRepository.save(member);

        return Optional.of(saveCheck).isPresent();
    }
}
