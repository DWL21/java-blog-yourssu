package com.yourssu.blog.comment.controller.dto;

import com.yourssu.blog.comment.service.dto.CommentRequest;
import com.yourssu.blog.comment.service.dto.CommentUpdateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentEditRequest(
        @NotBlank(message = "댓글 입력이 올바르지 않습니다.")
        @Size(max = 255, message = "댓글 입력이 255자를 초과할 수 없습니다.")
        String content) {

    public CommentUpdateRequest toCommentUpdateRequest(Long articleId, Long commentId, Long userId) {
        return new CommentUpdateRequest(new CommentRequest(articleId, commentId), userId, content);
    }
}
