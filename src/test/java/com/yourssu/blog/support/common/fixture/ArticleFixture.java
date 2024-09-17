package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.controller.dto.ArticleEditRequest;
import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.User;
import com.yourssu.blog.service.dto.ArticleDeleteRequest;
import com.yourssu.blog.service.dto.ArticleSaveRequest;
import com.yourssu.blog.service.dto.ArticleUpdateRequest;

public enum ArticleFixture {

    LEO("leo title",
            "leo context"
    ),
    EVOLVED_LEO("leo evolved title",
            "leo evolved context"
            );

    private final String title;
    private final String context;

    ArticleFixture(String title, String context) {
        this.title = title;
        this.context = context;
    }

    public Article getArticle(User user) {
        return new Article(user, title, context);
    }

    public ArticleCreateRequest getArticleCreateRequest() {
        return new ArticleCreateRequest(title, context);
    }

    public ArticleSaveRequest getArticleSaveRequest(Long userId) {
        return new ArticleSaveRequest(userId, title, context);
    }

    public ArticleEditRequest getArticleEditRequest() {
        return new ArticleEditRequest(title, context);
    }

    public ArticleUpdateRequest getArticleUpdateRequest(Long articleId, Long userId) {
        return new ArticleUpdateRequest(articleId, userId, title, context);
    }

    public ArticleDeleteRequest getArticleDeleteRequest(Long articleId, Long userId) {
        return new ArticleDeleteRequest(articleId, userId);
    }
}
