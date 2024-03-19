package com.example.membermanagement.global.jwt.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface JwtService {
    public Optional<String> extractAccessToken(HttpServletRequest request);
    public boolean isTokenValid(String token);
}
