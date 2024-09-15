package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.CommentRequest;
import com.yourssu.blog.service.dto.CommentUpdateRequest;

public record CommentEditRequest(String email, String password, String content) {

    public CommentUpdateRequest toCommentUpdateRequest(Long articleId, Long commentId) {
        return new CommentUpdateRequest(new CommentRequest(articleId, commentId), email, password, content);
    }
}
