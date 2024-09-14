package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.model.Article;
import com.yourssu.blog.service.dto.ArticleSaveRequest;
import org.apache.catalina.User;

public enum ArticleFixture {

    LEO(UserFixture.LEO,
            "leo title",
            "leo context"
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
}
