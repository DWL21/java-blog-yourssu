package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.ArticleSaveRequest;

public record ArticleCreateRequest(String email, String password, String title, String content) {

    public ArticleSaveRequest toArticleSaveRequest() {
        return new ArticleSaveRequest(email, password, title, content);
    }
}
