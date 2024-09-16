package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.UserSaveRequest;

public record UserCreateRequest(String email, String password, String username) {

    public UserSaveRequest toUserSaveRequest() {
        return new UserSaveRequest(email, password, username);
    }
}
