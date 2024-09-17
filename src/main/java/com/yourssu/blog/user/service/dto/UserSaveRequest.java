package com.yourssu.blog.user.service.dto;

import com.yourssu.blog.user.model.User;

public record UserSaveRequest(String email, String password, String username) {

    public User toUser() {
        return new User(email, password, username);
    }
}
