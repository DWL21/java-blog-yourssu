package com.yourssu.blog.controller.dto;

import com.yourssu.blog.service.dto.ArticleUpdateRequest;

public record ArticleEditRequest(String email, String password, String title, String content) {

    public ArticleUpdateRequest toArticleUpdatedRequest(Long articleId) {
        return new ArticleUpdateRequest(articleId, email, password, title, content);
    }
}
