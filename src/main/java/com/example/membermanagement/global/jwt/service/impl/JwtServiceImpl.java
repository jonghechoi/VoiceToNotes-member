package com.example.membermanagement.global.jwt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.membermanagement.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;

public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secretKey}")
    String secretKey;
    @Value("${jwt.access.header}")
    String accessHeader;

    /** JWT 표준 스펙 지정 */
    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String UID_CLAIM = "sub";
    private static final String BEARER = "Bearer ";

    @Override
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter()
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
}
