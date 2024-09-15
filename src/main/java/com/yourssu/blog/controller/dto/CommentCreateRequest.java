package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.CommentSaveRequest;

public record CommentCreateRequest(String email, String password, String content) {

    public CommentSaveRequest toCommentSaveRequest(Long articleId) {
        return new CommentSaveRequest(articleId, email, password, content);
    }
}
