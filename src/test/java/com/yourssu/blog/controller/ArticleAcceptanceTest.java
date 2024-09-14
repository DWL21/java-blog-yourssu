package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.service.dto.ArticleResponse;
import com.yourssu.blog.support.acceptance.AcceptanceTest;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.yourssu.blog.support.acceptance.AcceptanceContext.invokeGet;
import static com.yourssu.blog.support.acceptance.AcceptanceContext.invokePost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
class ArticleAcceptanceTest extends AcceptanceTest {

    @Test
    void 게시글_생성을_요청한다() {
        // Given
        ArticleFixture article = ArticleFixture.LEO;
        ArticleCreateRequest request = article.getArticleCreateRequest();

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
}