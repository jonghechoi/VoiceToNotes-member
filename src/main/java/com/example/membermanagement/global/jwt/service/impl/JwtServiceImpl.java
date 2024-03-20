package com.example.membermanagement.global.jwt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.membermanagement.global.jwt.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secretKey}")
    String secretKey;
    @Value("${jwt.access.header}")
    String accessHeader;

    /** JWT 표준 스펙 지정 */
    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
    private static final String UID_CLAIM = "sub";
    private static final String BEARER = "Bearer ";

    private JWTVerifier jwtVerifier;

    @Override
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(accessHeader))
                .filter(token -> token.startsWith("Bearer"))
                .map(token -> token.replace(BEARER, ""));
    }

    @Override
    public DecodedJWT isTokenValid(String token) {
        this.jwtVerifier = JWT.require(Algorithm.HMAC512(secretKey)).build();
        try {
            return jwtVerifier.verify(token);
        }
        catch (SignatureVerificationException e) {
            return null;
        }
    }

    @Override
    public String parseAccessToken(DecodedJWT decodedJWT) {
        String strJwt = decodedJWT.getClaim(UID_CLAIM).toString();
        String res = strJwt.replaceAll("\"", "");
        return res;
    }
}
