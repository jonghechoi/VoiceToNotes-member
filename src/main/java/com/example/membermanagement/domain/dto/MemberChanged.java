package com.example.membermanagement.domain.dto;

import com.example.membermanagement.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberChanged {
    private Long id;
    private String email;
    private String password;

    public MemberChanged(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword();
    }
}
