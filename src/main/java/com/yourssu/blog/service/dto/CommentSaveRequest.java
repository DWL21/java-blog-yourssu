package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.model.User;

public record CommentSaveRequest(Long articleId, Long userId, String content) {

    public Comment getComment(Article article, User user) {
        return new Comment(article, user, content);
    }
}
