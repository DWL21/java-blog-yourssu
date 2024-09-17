package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.User;

public record ArticleUpdateRequest(Long articleId, Long userId, String title, String content) {

    public Article getArticle(User user) {
        return new Article(articleId, user, title, content);
    }
}
