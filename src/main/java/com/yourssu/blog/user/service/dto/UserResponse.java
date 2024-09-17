package com.yourssu.blog.user.service.dto;

import com.yourssu.blog.user.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserResponse {

    private String email;
    private String username;

    private UserResponse(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public static UserResponse of(User user) {
        return new UserResponse(user.getEmail(), user.getUsername());
    }
}
