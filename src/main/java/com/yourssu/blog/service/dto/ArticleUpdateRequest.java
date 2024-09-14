package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;

public record ArticleUpdateRequest(Long articleId, String email, String password, String title, String content) {

    public Article getArticle() {
        return new Article(articleId, email, title, content);
    }
}
