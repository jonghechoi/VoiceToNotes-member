package com.example.membermanagement.global.jwt.filter;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.membermanagement.global.jwt.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.ExpressionException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(String.valueOf(request.getRequestURI()).equals("/api/user/join")) {
            filterChain.doFilter(request, response);
            return;
        }

        String uid = checkAccessTokenAuthorization(request, response, filterChain);
        log.info("토큰에 담긴 uid : {}", uid);
        request.setAttribute("uid", uid);

        filterChain.doFilter(request, response);
    }

    private String checkAccessTokenAuthorization(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        Optional<String> optionalAccessToken = jwtService.extractAccessToken(request);

        String accessToken = optionalAccessToken.orElseThrow(
                () -> new ExpressionException("토큰이 존재하지 않습니다.")
        );

        DecodedJWT decodedJWT = jwtService.isTokenValid(accessToken);
        return jwtService.parseAccessToken(decodedJWT);
    }
}
