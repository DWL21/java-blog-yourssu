package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;

public record ArticleSaveRequest(String email, String password, String title, String content) {

    public Article getArticle() {
        return new Article(email, title, content);
    }
}
