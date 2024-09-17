package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.User;

public record UserSaveRequest(String email, String password, String username) {

    public User toUser() {
        return new User(email, password, username);
    }
}
