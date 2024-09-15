package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.ArticleDeleteRequest;

public record ArticleRemoveRequest(String email, String password) {

    public ArticleDeleteRequest toArticleDeleteRequest(Long articleId) {
        return new ArticleDeleteRequest(articleId, email, password);
    }
}
