package com.yourssu.blog.service.dto;

public record CommentDeleteRequest(CommentRequest ids, String email, String password) {

    public Long commentId() {
        return ids.commentId();
    }
}
