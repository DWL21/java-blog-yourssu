package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.ArticleSaveRequest;

public record ArticleCreateRequest(String title, String content) {

    public ArticleSaveRequest toArticleSaveRequest(Long userId) {
        return new ArticleSaveRequest(userId, title, content);
    }
}
