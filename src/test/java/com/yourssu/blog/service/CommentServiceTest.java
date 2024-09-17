package com.yourssu.blog.service;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.User;
import com.yourssu.blog.model.repository.ArticleRepository;
import com.yourssu.blog.model.repository.UserRepository;
import com.yourssu.blog.service.dto.*;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.common.fixture.UserFixture;
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
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("댓글을 생성한다.")
    void save() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        CommentSaveRequest request = LEO.getCommentSaveRequest(article, user.getId());

        assertThatNoException().isThrownBy(
                () -> commentService.save(request)
        );
    }

    @Test
    @DisplayName("댓글을 수정한다.")
    void update() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article, user.getId()));

        CommentUpdateRequest request = EVOLVED_LEO.getCommentUpdateRequest(article, given.getCommentId(), user.getId());

        assertThatNoException().isThrownBy(
                () -> commentService.update(request)
        );
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void delete() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article, user.getId()));

        CommentDeleteRequest request = LEO.getCommentDeleteRequest(article, given.getCommentId(), user.getId());

        assertThatNoException().isThrownBy(
                () -> commentService.delete(request)
        );
    }

    private User saveUser(UserFixture user) {
        return userRepository.save(user.getUser());
    }

    private Article saveArticle(ArticleFixture article, User user) {
        return articleRepository.save(article.getArticle(user));
    }
}
