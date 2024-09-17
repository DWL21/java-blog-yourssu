package com.yourssu.blog.article.controller.dto;

import com.yourssu.blog.article.service.dto.ArticleUpdateRequest;

public record ArticleEditRequest(String title, String content) {

    public ArticleUpdateRequest toArticleUpdateRequest(Long articleId, Long userId) {
        return new ArticleUpdateRequest(articleId, userId, title, content);
    }
}
