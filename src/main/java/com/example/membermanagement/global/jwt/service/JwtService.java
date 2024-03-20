package com.example.membermanagement.global.jwt.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public interface JwtService {
    DecodedJWT isTokenValid(String token);
    Optional<String> extractAccessToken(HttpServletRequest request);
    String parseAccessToken(DecodedJWT decodedJWT);
}
