package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.CommentDeleteRequest;
import com.yourssu.blog.service.dto.CommentRequest;

public record CommentRemoveRequest(String email, String password) {

    public CommentDeleteRequest toDeleteRequest(Long articleId, Long commentId) {
        return new CommentDeleteRequest(new CommentRequest(articleId, commentId), email, password);
    }
}
