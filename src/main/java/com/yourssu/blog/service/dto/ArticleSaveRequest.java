package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.User;

public record ArticleSaveRequest(Long userId, String title, String content) {

    public Article getArticle(User user) {
        return new Article(user, title, content);
    }
}
