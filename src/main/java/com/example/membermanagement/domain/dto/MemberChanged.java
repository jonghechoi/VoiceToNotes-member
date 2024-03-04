package com.example.membermanagement.domain.dto;

import com.example.membermanagement.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberChanged {
    private Long id;
    private String uid;
    private String password;
    private String name;
    private String role;

    public MemberChanged(Member member) {
        this.id = member.getId();
        this.uid = member.getUid();
        this.password = member.getPassword();
        this.name = member.getName();
        this.role = member.getRole();
    }
}
