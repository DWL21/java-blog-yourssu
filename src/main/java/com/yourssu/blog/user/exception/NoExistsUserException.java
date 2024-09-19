package com.yourssu.blog.user.exception;

import com.yourssu.blog.common.exception.NotFoundException;

public class NoExistsUserException extends NotFoundException {

    public NoExistsUserException() {
        super("회원가입되지 않은 이메일입니다.");
    }
}
