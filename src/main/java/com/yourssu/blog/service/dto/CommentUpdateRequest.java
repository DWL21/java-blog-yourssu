package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;

public record CommentUpdateRequest(CommentRequest ids, String email, String password, String content) {

    public Comment getComment(Article article) {
        return new Comment(ids.commentId(), article, email, content);
    }

    public Long articleId() {
        return ids.articleId();
    }

    public Long commentId() {
        return ids.commentId();
    }
}
