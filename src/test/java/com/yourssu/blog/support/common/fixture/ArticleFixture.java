package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.controller.dto.ArticleEditRequest;
import com.yourssu.blog.controller.dto.ArticleRemoveRequest;
import com.yourssu.blog.model.Article;
import com.yourssu.blog.service.dto.ArticleDeleteRequest;
import com.yourssu.blog.service.dto.ArticleSaveRequest;
import com.yourssu.blog.service.dto.ArticleUpdateRequest;

public enum ArticleFixture {

    LEO(UserFixture.LEO,
            "leo title",
            "leo context"
    ),
    EVOLVED_LEO(UserFixture.LEO,
            "leo evolved title",
            "leo evolved context"
            );

    private final UserFixture userFixture;
    private final String title;
    private final String context;

    ArticleFixture(UserFixture userFixture, String title, String context) {
        this.userFixture = userFixture;
        this.title = title;
        this.context = context;
    }

    public Article getArticle() {
        return new Article(userFixture.getEmail(), title, context);
    }

    public ArticleCreateRequest getArticleCreateRequest() {
        return new ArticleCreateRequest(userFixture.getEmail(), userFixture.getPassword(), title, context);
    }

    public ArticleSaveRequest getArticleSaveRequest() {
        return new ArticleSaveRequest(userFixture.getEmail(), userFixture.getPassword(), title, context);
    }

    public ArticleEditRequest getArticleEditRequest() {
        return new ArticleEditRequest(userFixture.getEmail(), userFixture.getPassword(), title, context);
    }

    public ArticleUpdateRequest getArticleUpdateRequest(Long articleId) {
        return new ArticleUpdateRequest(articleId, userFixture.getEmail(), userFixture.getPassword(), title, context);
    }

    public ArticleRemoveRequest getArticleRemoveRequest() {
        return new ArticleRemoveRequest(userFixture.getEmail(), userFixture.getPassword());
    }

    public ArticleDeleteRequest getArticleDeleteRequest(Long articleId) {
        return new ArticleDeleteRequest(articleId, userFixture.getEmail(), userFixture.getPassword());
    }

}
