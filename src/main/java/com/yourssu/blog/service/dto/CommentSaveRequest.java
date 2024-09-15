package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;

public record CommentSaveRequest(Long articleId, String email, String password, String content) {

    public Comment getComment(Article article) {
        return new Comment(article, email, content);
    }
}
