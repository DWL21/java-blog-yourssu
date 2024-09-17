package com.yourssu.blog.article.service.dto;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.user.model.User;

public record ArticleSaveRequest(Long userId, String title, String content) {

    public Article getArticle(User user) {
        return new Article(user, title, content);
    }
}
