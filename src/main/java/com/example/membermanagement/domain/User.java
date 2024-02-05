package com.example.membermanagement.domain;

import com.example.membermanagement.domain.dto.UserRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    private String id;
    private String password;
    private String email;
    private String name;
    private String birth;
    private Integer gender;
    private String country;
    private String phone;

    public static User createUser(UserRequestDto requestDto) {
        User user = new User();
        user.setId(requestDto.getId());
        user.setPassword(requestDto.getPassword());
        user.setEmail(requestDto.getEmail());
        user.setName(requestDto.getName());
        user.setBirth(requestDto.getBirth());
        user.setGender(requestDto.getGender());
        user.setCountry(requestDto.getCountry());
        user.setPhone(requestDto.getPhone());

        return user;
    }
}
