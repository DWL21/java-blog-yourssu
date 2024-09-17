package com.yourssu.blog.article.controller.dto;

import com.yourssu.blog.article.service.dto.ArticleSaveRequest;

public record ArticleCreateRequest(String title, String content) {

    public ArticleSaveRequest toArticleSaveRequest(Long userId) {
        return new ArticleSaveRequest(userId, title, content);
    }
}
