package com.yourssu.blog.user.controller.dto;

import com.yourssu.blog.user.service.dto.TokenIssueRequest;

public record AuthenticateRequest(String email, String password) {

    public TokenIssueRequest toTokenIssueRequest() {
        return new TokenIssueRequest(email, password);
    }
}
