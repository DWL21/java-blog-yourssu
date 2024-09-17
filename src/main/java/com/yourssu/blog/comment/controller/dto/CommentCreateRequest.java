package com.yourssu.blog.comment.controller.dto;

import com.yourssu.blog.comment.service.dto.CommentSaveRequest;

public record CommentCreateRequest(String content) {

    public CommentSaveRequest toCommentSaveRequest(Long articleId, Long userId) {
        return new CommentSaveRequest(articleId, userId, content);
    }
}
