package com.yourssu.blog.service;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.repository.ArticleRepository;
import com.yourssu.blog.service.dto.*;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.CommentFixture.EVOLVED_LEO;
import static com.yourssu.blog.support.common.fixture.CommentFixture.LEO;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ApplicationTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("댓글을 생성한다.")
    void save() {
        Article article = saveArticle(ArticleFixture.LEO);
        CommentSaveRequest request = LEO.getCommentSaveRequest(article);

        assertThatNoException().isThrownBy(
                () -> commentService.save(request)
        );
    }

    @Test
    @DisplayName("댓글을 수정한다.")
    void update() {
        Article article = saveArticle(ArticleFixture.LEO);
        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article));

        CommentUpdateRequest request = EVOLVED_LEO.getCommentUpdateRequest(article, given.getCommentId());

        assertThatNoException().isThrownBy(
                () -> commentService.update(request)
        );
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void delete() {
        Article article = saveArticle(ArticleFixture.LEO);
        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article));

        CommentDeleteRequest request = LEO.getCommentDeleteRequest(article, given.getCommentId());

        assertThatNoException().isThrownBy(
                () -> commentService.delete(request)
        );
    }

    private Article saveArticle(ArticleFixture article) {
        return articleRepository.save(article.getArticle());
    }
}
