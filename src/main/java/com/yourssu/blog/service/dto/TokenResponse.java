package com.yourssu.blog.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TokenResponse {

    private String token;

    private TokenResponse(String token) {
        this.token = token;
    }

    public static TokenResponse of(String token) {
        return new TokenResponse(token);
    }
}
