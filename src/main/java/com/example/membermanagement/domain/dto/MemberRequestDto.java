package com.example.membermanagement.domain.dto;

import com.example.membermanagement.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    @Email(message = "[Request] 이메일의 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "[Request] 비밀번호의 형식이 올바르지 않습니다.")
    private String password;

    public static Member toEntity(MemberRequestDto requestDto) {
        Member member = new Member();
        member.setEmail(requestDto.getEmail());
        member.setPassword(requestDto.getPassword());

        return member;
    }
}
