package com.yourssu.blog.user.exception;

import com.yourssu.blog.common.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("가입되지 않은 회원입니다.");
    }
}
