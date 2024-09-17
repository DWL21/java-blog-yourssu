package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.CommentSaveRequest;

public record CommentCreateRequest(String content) {

    public CommentSaveRequest toCommentSaveRequest(Long articleId, Long userId) {
        return new CommentSaveRequest(articleId, userId, content);
    }
}
