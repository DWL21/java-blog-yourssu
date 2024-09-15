package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.CommentCreateRequest;
import com.yourssu.blog.controller.dto.CommentEditRequest;
import com.yourssu.blog.service.dto.ArticleResponse;
import com.yourssu.blog.service.dto.CommentResponse;
import com.yourssu.blog.support.acceptance.AcceptanceTest;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.common.fixture.CommentFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.yourssu.blog.support.acceptance.AcceptanceContext.invokePost;
import static com.yourssu.blog.support.acceptance.AcceptanceContext.invokePut;
import static com.yourssu.blog.support.common.fixture.CommentFixture.EVOLVED_LEO;
import static com.yourssu.blog.support.common.fixture.CommentFixture.LEO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
public class CommentAcceptanceTest extends AcceptanceTest {

    @Test
    void 댓글_생성을_요청한다() {
        // Given
        ArticleResponse article = ArticleAcceptanceTest.createArticle(ArticleFixture.LEO);
        CommentCreateRequest request = LEO.getCommentCreateRequest();

        // When
        var response = invokePost(generateCommentRequestUri(article.getArticleId()), request);

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
        ArticleResponse article = ArticleAcceptanceTest.createArticle(ArticleFixture.LEO);
        CommentResponse comment = createComment(article.getArticleId(), LEO);

        // When
        CommentEditRequest request = EVOLVED_LEO.getCommentEditRequest();

        var response = invokePut(generateCommentRequestUri(article.getArticleId(), comment.getCommentId()), request);
        CommentResponse actual = response.as(CommentResponse.class);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(actual.getContent()).isEqualTo(request.content())
        );
    }

    private static String generateCommentRequestUri(Long articleId) {
        return "/api/articles/" + articleId + "/comments";
    }

    private static String generateCommentRequestUri(Long articleId, Long commentId) {
        return "/api/articles/" + articleId + "/comments/" + commentId;
    }

    public static CommentResponse createComment(Long articleId, CommentFixture comment) {
        CommentCreateRequest request = comment.getCommentCreateRequest();
        return invokePost(generateCommentRequestUri(articleId), request).as(CommentResponse.class);
    }
}
