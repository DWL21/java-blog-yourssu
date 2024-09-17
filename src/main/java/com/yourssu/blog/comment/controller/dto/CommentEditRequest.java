package com.yourssu.blog.comment.controller.dto;

import com.yourssu.blog.comment.service.dto.CommentRequest;
import com.yourssu.blog.comment.service.dto.CommentUpdateRequest;

public record CommentEditRequest(String content) {

    public CommentUpdateRequest toCommentUpdateRequest(Long articleId, Long commentId, Long userId) {
        return new CommentUpdateRequest(new CommentRequest(articleId, commentId), userId, content);
    }
}
