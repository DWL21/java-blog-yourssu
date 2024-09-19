package com.yourssu.blog.comment.exception;

import com.yourssu.blog.common.exception.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException() {
        super("존재하지 않는 댓글입니다.");
    }
}
