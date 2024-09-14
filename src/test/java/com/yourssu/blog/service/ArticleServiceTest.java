package com.yourssu.blog.service;

import com.yourssu.blog.service.dto.ArticleDeleteRequest;
import com.yourssu.blog.service.dto.ArticleResponse;
import com.yourssu.blog.service.dto.ArticleSaveRequest;
import com.yourssu.blog.service.dto.ArticleUpdateRequest;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.ArticleFixture.*;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ApplicationTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    @DisplayName("게시글을 생성한다.")
    void save() {
        ArticleSaveRequest request = LEO.getArticleSaveRequest();

        assertThatNoException().isThrownBy(
                () -> articleService.saveArticle(request)
        );
    }

    @Test
    @DisplayName("게시글을 수정한다.")
    void update() {
        ArticleResponse given = articleService.saveArticle(LEO.getArticleSaveRequest());

        ArticleUpdateRequest request = EVOLVED_LEO.getArticleUpdateRequest(given.getArticleId());

        assertThatNoException().isThrownBy(
                () -> articleService.update(request)
        );
    }

    @Test
    @DisplayName("게시글을 삭제한다.")
    void delete() {
        ArticleResponse given = articleService.saveArticle(LEO.getArticleSaveRequest());

        ArticleDeleteRequest request = LEO.getArticleDeleteRequest(given.getArticleId());

        assertThatNoException().isThrownBy(
                () -> articleService.delete(request)
        );
    }
}