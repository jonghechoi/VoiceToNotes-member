package com.example.membermanagement.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "[Request] 아이디의 형식이 올바르지 않습니다.")
    private String id;
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
}
