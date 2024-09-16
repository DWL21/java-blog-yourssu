package com.yourssu.blog.config.support;

import com.yourssu.blog.support.service.ApplicationTest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ApplicationTest
class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("토큰을 생성한다.")
    void generateToken() {
        assertThatNoException().isThrownBy(
                () -> jwtTokenProvider.generateToken(
                        Jwts.claims()
                        .build())
        );
    }

    @Test
    @DisplayName("토큰을 인증하고 payload를 반환한다.")
    void extractAllClaims() {
        final String expected = "This is a token.";
        Claims claims = Jwts.claims()
                .subject(expected)
                .build();
        String token = jwtTokenProvider.generateToken(claims);

        String actual = jwtTokenProvider.extractAllClaims(token).getSubject();

        assertThat(actual).isEqualTo(expected);
    }
}