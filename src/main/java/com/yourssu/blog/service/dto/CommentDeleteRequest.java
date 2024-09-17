package com.yourssu.blog.service.dto;

public record CommentDeleteRequest(CommentRequest ids, Long userId) {

    public Long articleId() {
        return ids.articleId();
    }

    public Long commentId() {
        return ids.commentId();
    }
}
