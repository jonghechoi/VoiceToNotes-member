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

    @NotBlank(message = "[Request] 아이디의 형식이 올바르지 않습니다.")
    private String uid;

    @NotBlank(message = "[Request] 비밀번호의 형식이 올바르지 않습니다.")
    private String password;

    @Email(message = "[Request] 이메일의 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "[Request] 이름의 형식이 올바르지 않습니다.")
    private String name;

    @Pattern(regexp = "\\d{8}", message = "[Request] Invalid Birth Format")
    @NotNull(message = "[Request] 생년월일의 형식이 올바르지 않습니다.")
    private String birth;

    private Integer gender;

    private String country;

    @NotBlank(message = "[Request] 핸드폰 번호의 형식이 올바르지 않습니다.")
    private String phone;

    private String group;

    private String role;

    public static Member toEntity(MemberRequestDto requestDto) {
        Member member = new Member();
        member.setUid(requestDto.getUid());
        member.setPassword(requestDto.getPassword());
        member.setEmail(requestDto.getEmail());
        member.setName(requestDto.getName());
        member.setBirth(requestDto.getBirth());
        member.setGender(requestDto.getGender());
        member.setCountry(requestDto.getCountry());
        member.setPhone(requestDto.getPhone());
        member.setGroup(requestDto.getGroup());
        member.setRole(requestDto.getRole());

        return member;
    }
}
