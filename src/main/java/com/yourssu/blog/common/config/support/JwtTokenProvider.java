package com.yourssu.blog.common.config.support;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final long expirationTime;

    private static final String TOKEN_SIGN = "Bearer ";

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") final String secretKey,
                            @Value("${security.jwt.token.expiration-time}") final Long expirationTime) {
        this.secretKey = getSigningKey(secretKey);
        this.expirationTime = expirationTime;
    }

    private static SecretKey getSigningKey(String secretKey) {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(Claims claims) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(trimBearer(token))
                .getPayload();
    }

    private String trimBearer(String token) {
        if (token.startsWith(TOKEN_SIGN)) {
            return token.substring(TOKEN_SIGN.length());
        }
        return token;
    }
}
