package com.yourssu.blog.comment.service.dto;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.comment.model.Comment;
import com.yourssu.blog.user.model.User;

public record CommentSaveRequest(Long articleId, Long userId, String content) {

    public Comment getComment(Article article, User user) {
        return new Comment(article, user, content);
    }
}
