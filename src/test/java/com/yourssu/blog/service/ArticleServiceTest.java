package com.yourssu.blog.service;

import com.yourssu.blog.service.dto.ArticleSaveRequest;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatNoException;

@ApplicationTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("게시글을 생성한다.")
    void save() {
        ArticleFixture article = ArticleFixture.LEO;
        ArticleSaveRequest request = article.getArticleSaveRequest();

        assertThatNoException().isThrownBy(
                () -> articleService.saveArticle(request)
        );
    }
}