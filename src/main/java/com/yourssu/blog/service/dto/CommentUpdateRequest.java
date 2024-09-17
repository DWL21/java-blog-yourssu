package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.model.User;

public record CommentUpdateRequest(CommentRequest ids, Long userId, String content) {

    public Comment getComment(Article article, User user) {
        return new Comment(ids.commentId(), article, user, content);
    }

    public Long articleId() {
        return ids.articleId();
    }

    public Long commentId() {
        return ids.commentId();
    }
}
