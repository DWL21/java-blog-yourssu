package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.user.controller.dto.AuthenticateRequest;
import com.yourssu.blog.user.controller.dto.UserCreateRequest;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.user.service.dto.TokenIssueRequest;
import com.yourssu.blog.user.service.dto.UserSaveRequest;

public enum UserFixture {

    LEO("leo@yourssu.com",
            "leoPassword",
            "leo"
    ),
    LEO_EMAIL_INCORRECT("incorrect-email",
            "password",
            "leo"),

    LEO_PASSWORD_INCORRECT("leo@yourssu.com",
            "incorrectPassword",
            "leo"
    ),
    MIO("mio@yourssu.com",
            "mioPassword",
            "mio"),
    ;

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
