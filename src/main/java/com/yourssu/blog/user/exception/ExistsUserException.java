package com.yourssu.blog.user.exception;

import com.yourssu.blog.common.exception.ConflictException;

public class ExistsUserException extends ConflictException {

    public ExistsUserException() {
        super("이미 가입된 회원의 이메일입니다.");
    }
}
