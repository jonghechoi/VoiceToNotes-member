package com.example.membermanagement.adaptor.impl;

import com.example.membermanagement.domain.Member;
import com.example.membermanagement.domain.dto.MemberRequestDto;
import com.example.membermanagement.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailProducerImplTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MailSender mailSender;
    @Mock
    private SimpleMailMessage templateMessage;
    @InjectMocks
    private EmailProducerImpl emailProducer;

    @Test
    @DisplayName("기존에 등록된 이메일인지 확인")
    void checkDuplicateEmail() {
        // given
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setEmail("test11@naver.com");
        memberRequestDto.setPassword("1234");

        Member member = MemberRequestDto.toEntity(memberRequestDto);
        when(memberRepository.findMemberByEmail("test11@naver.com"))
                .thenReturn(Optional.of(member));

        // when
        Optional<Member> memberCheck = memberRepository.findMemberByEmail("test11@naver.com");

        // then
        assertTrue(memberCheck.isPresent());
    }

    @Test
    @DisplayName("메시지 전달")
    void templateMessage() {
        // given
        char[] arr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int min = 0;
        int max = 10;
        int random;
        StringBuffer sb = new StringBuffer();

        // when
        for(int i=0; i<6; i++) {
            random = (int)((Math.random() * (max - min)) + min);
            sb.append(arr[random]);
        }

        // then
        assertEquals(6, sb.length());
    }

    @Test
    @DisplayName("이메일 전송")
    void sendEmail() {
        // given
        // when
        // then

    }
}
