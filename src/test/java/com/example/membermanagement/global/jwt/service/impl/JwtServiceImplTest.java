package com.example.membermanagement.global.jwt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class JwtServiceImplTest {
    @Value("${jwt.secretKey}")
    String secretKey;
    @Value("${jwt.access.header}")
    String accessHeader;

    /** JWT 표준 스펙 지정 */
    private static final String UID_CLAIM = "sub";
    private static final String BEARER = "Bearer ";

    private JWTVerifier jwtVerifier;
    private String accessToken;

    @BeforeEach
    void test() {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        this.jwtVerifier = JWT.require(algorithm).build();
        this.accessToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhYWEiLCJleHAiOjE3MTA5MTExMTF9.C4sV00lPzbOEHUp4KAW5x3shLFXDd-phS-leMz1xSva6ODHi4NTeLxTWAceUdohVAb0Es4tY-ohbEuywbfo-BA";
    }

    @Test
    @DisplayName("Extract AccessToken")
    void extractAccessToken() {
        // given
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(accessHeader, BEARER + accessToken);

        // when
        Optional<String> header = Optional.ofNullable(mockRequest.getHeader(accessHeader))
                .filter(token -> token.startsWith("Bearer"))
                .map(token -> token.replace(BEARER, ""));

        // then
        Optional<DecodedJWT> decodedJWT;
        try {
            decodedJWT = Optional.ofNullable(jwtVerifier.verify(header.get()));
        }
        catch (SignatureVerificationException e) {
            decodedJWT = Optional.empty();
        }

        assertThat(decodedJWT.get().getToken()).isEqualTo(accessToken);
    }

    @Test
    @DisplayName("Token Valid Check")
    void isTokenValid() {
        // then
        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(accessToken);
        }
        catch (SignatureVerificationException e) {
            decodedJWT = null;
        }

        assertThat(decodedJWT).isNotNull();
    }

    @Test
    @DisplayName("Parse AccessToken")
    void parseAccessToken() {
        // when
        DecodedJWT decodedJWT;
        try {
            decodedJWT = jwtVerifier.verify(accessToken);
        }
        catch (SignatureVerificationException e) {
            decodedJWT = null;
        }

        // then
        String strJwt = decodedJWT.getClaim(UID_CLAIM).toString();
        String res = strJwt.replaceAll("\"", "");

        assertThat(res).isEqualTo("aaa");
    }
}