package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.controller.dto.AuthenticateRequest;
import com.yourssu.blog.controller.dto.UserCreateRequest;
import com.yourssu.blog.model.User;
import com.yourssu.blog.service.dto.TokenIssueRequest;
import com.yourssu.blog.service.dto.UserSaveRequest;

public enum UserFixture {

    LEO("leo@yourssu.com",
            "leoPassword",
            "leo"
    );

    private final String email;
    private final String password;
    private final String username;

    UserFixture(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User getUser() {
        return new User(email, password, username);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserCreateRequest getUserCreateRequest() {
        return new UserCreateRequest(email, password, username);
    }

    public UserSaveRequest getUserSaveRequest() {
        return new UserSaveRequest(email, password, username);
    }

    public AuthenticateRequest getAuthenticateRequest() {
        return new AuthenticateRequest(email, password);
    }

    public TokenIssueRequest getTokenIssueRequest() {
        return new TokenIssueRequest(email, password);
    }
}
