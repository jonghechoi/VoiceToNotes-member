package com.example.membermanagement.service.impl;

import com.example.membermanagement.domain.User;
import com.example.membermanagement.domain.dto.UserRequestDto;
import com.example.membermanagement.repository.UserRepository;
import com.example.membermanagement.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {
    private final UserRepository userRepository;

    @Override
    public boolean joinUser(UserRequestDto userRequestDto) {
        return Optional.of(userRepository.save(User.createUser(userRequestDto))).isPresent();
    }
}
