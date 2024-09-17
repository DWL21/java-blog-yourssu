package com.yourssu.blog.user.controller.dto;

import com.yourssu.blog.user.service.dto.UserSaveRequest;

public record UserCreateRequest(String email, String password, String username) {

    public UserSaveRequest toUserSaveRequest() {
        return new UserSaveRequest(email, password, username);
    }
}
