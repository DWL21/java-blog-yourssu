package com.yourssu.blog.user.controller.dto;

import com.yourssu.blog.user.service.dto.UserSaveRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @NotBlank(message = "이메일 입력이 올바르지 않습니다.")
        @Size(max = 255, message = "이메일 입력이 255자를 초과할 수 없습니다.")
        String email,

        @NotBlank(message = "비밀번호 입력이 올바르지 않습니다.")
        @Size(max = 255, message = "비밀번호 입력이 255자를 초과할 수 없습니다.")
        String password,

        @NotBlank(message = "이름 입력이 올바르지 않습니다.")
        @Size(max = 255, message = "이름 입력이 255자를 초과할 수 없습니다.")
        String username) {

    public UserSaveRequest toUserSaveRequest() {
        return new UserSaveRequest(email, password, username);
    }
}
