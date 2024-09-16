package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.TokenIssueRequest;

public record AuthenticateRequest(String email, String password) {

    public TokenIssueRequest toTokenIssueRequest() {
        return new TokenIssueRequest(email, password);
    }
}
