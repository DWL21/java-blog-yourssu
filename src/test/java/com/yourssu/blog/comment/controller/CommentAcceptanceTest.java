package com.yourssu.blog.comment.controller;

import com.yourssu.blog.article.controller.ArticleAcceptanceTest;
import com.yourssu.blog.comment.controller.dto.CommentCreateRequest;
import com.yourssu.blog.comment.controller.dto.CommentEditRequest;
import com.yourssu.blog.article.service.dto.ArticleResponse;
import com.yourssu.blog.comment.service.dto.CommentResponse;
import com.yourssu.blog.support.acceptance.AcceptanceTest;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.common.fixture.CommentFixture;
import com.yourssu.blog.support.common.fixture.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.yourssu.blog.user.controller.UserAcceptanceTest.authenticate;
import static com.yourssu.blog.user.controller.UserAcceptanceTest.createUser;
import static com.yourssu.blog.support.acceptance.AcceptanceContext.*;
import static com.yourssu.blog.support.common.fixture.CommentFixture.EVOLVED_LEO;
import static com.yourssu.blog.support.common.fixture.CommentFixture.LEO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
public class CommentAcceptanceTest extends AcceptanceTest {

    @Test
    void 댓글_작성을_요청한다() {
        // Given
        createUser(UserFixture.LEO);
        String token = authenticate(UserFixture.LEO).getToken();
        ArticleResponse article = ArticleAcceptanceTest.createArticle(ArticleFixture.LEO, token);
        CommentCreateRequest request = LEO.getCommentCreateRequest();

        // When
        var response = invokePostWithToken(generateCommentRequestUri(article.getArticleId()), token, request);

        //Then
        CommentResponse actual = response.as(CommentResponse.class);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(actual.getCommentId()).isEqualTo(1)
        );
    }

    @Test
    void 댓글_수정을_요청한다() {
        // Given
        createUser(UserFixture.LEO);
        String token = authenticate(UserFixture.LEO).getToken();
        ArticleResponse article = ArticleAcceptanceTest.createArticle(ArticleFixture.LEO, token);
        CommentResponse comment = createComment(article.getArticleId(), LEO, token);

        // When
        CommentEditRequest request = EVOLVED_LEO.getCommentEditRequest();

        var response = invokePutWihToken(generateCommentRequestUri(article.getArticleId(), comment.getCommentId()), token, request);
        CommentResponse actual = response.as(CommentResponse.class);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(actual.getContent()).isEqualTo(request.content())
        );
    }

    @Test
    void 댓글_삭제를_요청한다() {
        // Given
        createUser(UserFixture.LEO);
        String token = authenticate(UserFixture.LEO).getToken();
        ArticleResponse article = ArticleAcceptanceTest.createArticle(ArticleFixture.LEO, token);
        CommentResponse comment = createComment(article.getArticleId(), LEO, token);

        // When
        var response = invokeDeleteWithToken(generateCommentRequestUri(article.getArticleId(), comment.getCommentId()), token);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value())
        );
    }

    private static String generateCommentRequestUri(Long articleId) {
        return "/api/articles/" + articleId + "/comments";
    }

    private static String generateCommentRequestUri(Long articleId, Long commentId) {
        return "/api/articles/" + articleId + "/comments/" + commentId;
    }

    private static CommentResponse createComment(Long articleId, CommentFixture comment, String token) {
        CommentCreateRequest request = comment.getCommentCreateRequest();
        return invokePostWithToken(generateCommentRequestUri(articleId), token, request).as(CommentResponse.class);
    }
}
