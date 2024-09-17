package com.yourssu.blog.article.controller;

import com.yourssu.blog.article.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.article.controller.dto.ArticleEditRequest;
import com.yourssu.blog.article.service.dto.ArticleResponse;
import com.yourssu.blog.support.acceptance.AcceptanceTest;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.common.fixture.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.yourssu.blog.user.controller.UserAcceptanceTest.authenticate;
import static com.yourssu.blog.user.controller.UserAcceptanceTest.createUser;
import static com.yourssu.blog.support.acceptance.AcceptanceContext.*;
import static com.yourssu.blog.support.common.fixture.ArticleFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
public class ArticleAcceptanceTest extends AcceptanceTest {

    @Test
    void 게시글_작성을_요청한다() {
        // Given
        createUser(UserFixture.LEO);
        String token = authenticate(UserFixture.LEO).getToken();
        ArticleCreateRequest request = LEO.getArticleCreateRequest();

        // When
        invokePostWithToken("/api/articles", token, request);

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
        createUser(UserFixture.LEO);
        String token = authenticate(UserFixture.LEO).getToken();
        ArticleResponse article = createArticle(LEO, token);

        // When
        ArticleEditRequest request = EVOLVED_LEO.getArticleEditRequest();

        var response = invokePutWihToken("/api/articles/" + article.getArticleId(), token, request);
        ArticleResponse actual = response.as(ArticleResponse.class);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(actual.getTitle()).isEqualTo(request.title())
        );
    }

    @Test
    void 게시글_삭제를_요청한다() {
        // Given
        createUser(UserFixture.LEO);
        String token = authenticate(UserFixture.LEO).getToken();
        ArticleResponse article = createArticle(LEO, token);

        // When
        var response = invokeDeleteWithToken("/api/articles/" + article.getArticleId(), token);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value())
        );
    }

    public static ArticleResponse createArticle(ArticleFixture article, String token) {
        ArticleCreateRequest request = article.getArticleCreateRequest();
        return invokePostWithToken("/api/articles", token, request).as(ArticleResponse.class);
    }
}