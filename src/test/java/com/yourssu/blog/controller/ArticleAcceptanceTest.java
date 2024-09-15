package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.controller.dto.ArticleEditRequest;
import com.yourssu.blog.controller.dto.ArticleRemoveRequest;
import com.yourssu.blog.service.dto.ArticleResponse;
import com.yourssu.blog.support.acceptance.AcceptanceTest;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.yourssu.blog.support.acceptance.AcceptanceContext.*;
import static com.yourssu.blog.support.common.fixture.ArticleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
class ArticleAcceptanceTest extends AcceptanceTest {

    @Test
    void 게시글_생성을_요청한다() {
        // Given
        ArticleCreateRequest request = LEO.getArticleCreateRequest();

        // When
        invokePost("/api/articles", request);

        //Then
        var response = invokeGet("/api/articles/1");
        ArticleResponse actual = response.as(ArticleResponse.class);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value()),
                () -> assertThat(actual.getArticleId()).isEqualTo(1)
        );
    }

    @Test
    void 게시글_수정을_요청한다() {
        // Given
        ArticleResponse article = createArticle(LEO);

        // When
        ArticleEditRequest request = EVOLVED_LEO.getArticleEditRequest();

        var response = invokePut("/api/articles/" + article.getArticleId(), request);
        ArticleResponse actual = response.as(ArticleResponse.class);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(actual.getTitle()).isEqualTo(request.title())
        );
    }

    @Test
    void 게시글_삭제를_요청한다() {
        // Given
        ArticleResponse article = createArticle(LEO);

        // When
        ArticleRemoveRequest request = LEO.getArticleRemoveRequest();

        var response = invokeDelete("/api/articles/" + article.getArticleId(), request);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value())
        );
    }

    public static ArticleResponse createArticle(ArticleFixture article) {
        ArticleCreateRequest request = article.getArticleCreateRequest();
        return invokePost("/api/articles", request).as(ArticleResponse.class);
    }
}