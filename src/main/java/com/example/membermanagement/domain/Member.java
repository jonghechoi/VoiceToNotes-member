package com.example.membermanagement.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    private Long id;


    private String uid;

    private String password;

    private String email;

    private String name;

    private String birth;

    private Integer gender;

    private String country;

    private String phone;

    @Column(name = "member_group")
    private String group;

    private String role;
}
