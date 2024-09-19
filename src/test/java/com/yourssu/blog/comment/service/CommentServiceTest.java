package com.yourssu.blog.comment.service;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.comment.service.dto.CommentDeleteRequest;
import com.yourssu.blog.comment.service.dto.CommentResponse;
import com.yourssu.blog.comment.service.dto.CommentSaveRequest;
import com.yourssu.blog.comment.service.dto.CommentUpdateRequest;
import com.yourssu.blog.common.exception.ForbiddenException;
import com.yourssu.blog.common.exception.InvalidRequestException;
import com.yourssu.blog.common.exception.NotFoundException;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.article.model.repository.ArticleRepository;
import com.yourssu.blog.user.model.repository.UserRepository;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.common.fixture.UserFixture;
import com.yourssu.blog.support.service.ApplicationTest;
import org.hibernate.annotations.NotFoundAction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.CommentFixture.EVOLVED_LEO;
import static com.yourssu.blog.support.common.fixture.CommentFixture.LEO;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        CommentSaveRequest request = LEO.getCommentSaveRequest(article.getId(), user.getId());

        assertThatNoException().isThrownBy(
                () -> commentService.save(request)
        );
    }

    @Test
    @DisplayName("존재하지 않는 게시글 번호일 경우 예외를 반환한다.")
    void saveWhenNotFoundArticle() {
        User user = saveUser(UserFixture.LEO);
        CommentSaveRequest request = LEO.getCommentSaveRequest(1L, user.getId());

        assertThatThrownBy(() -> commentService.save(request))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("댓글을 수정한다.")
    void update() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article.getId(), user.getId()));

        CommentUpdateRequest request = EVOLVED_LEO.getCommentUpdateRequest(article, given.getCommentId(), user.getId());

        assertThatNoException().isThrownBy(
                () -> commentService.update(request)
        );
    }

    @Test
    @DisplayName("존재하지 않는 댓글 번호일 경우 예외를 반환한다.")
    void updateWhenNotFoundComment() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);

        CommentUpdateRequest request = EVOLVED_LEO.getCommentUpdateRequest(article, 1L, user.getId());

        assertThatThrownBy(() -> commentService.update(request))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("게시글 번호에 해당하지 않는 댓글 번호일 경우 예외를 반환한다.")
    void updateWhenInvalidRequest() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        CommentResponse comment = commentService.save(LEO.getCommentSaveRequest(article.getId(), user.getId()));
        Article anotherArticle = saveArticle(ArticleFixture.LEO, user);

        CommentUpdateRequest request = EVOLVED_LEO.getCommentUpdateRequest(anotherArticle, comment.getCommentId(), user.getId());

        assertThatThrownBy(() -> commentService.update(request))
                .isInstanceOf(InvalidRequestException.class);
    }

    @Test
    @DisplayName("댓글 작성자의 요청이 아닌 경우 예외를 반환한다.")
    void updateWhenForbidden() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        User anotherUser = saveUser(UserFixture.MIO);

        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article.getId(), user.getId()));

        CommentUpdateRequest request = EVOLVED_LEO.getCommentUpdateRequest(article, given.getCommentId(), anotherUser.getId());

        assertThatThrownBy(() -> commentService.update(request))
                .isInstanceOf(ForbiddenException.class);
    }

    @Test
    @DisplayName("댓글을 삭제한다.")
    void delete() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article.getId(), user.getId()));

        CommentDeleteRequest request = LEO.getCommentDeleteRequest(article, given.getCommentId(), user.getId());

        assertThatNoException().isThrownBy(
                () -> commentService.delete(request)
        );
    }

    @Test
    @DisplayName("존재하지 않는 댓글 번호일 경우 예외를 반환한다.")
    void deleteWhenNotFoundComment() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);

        CommentDeleteRequest request = EVOLVED_LEO.getCommentDeleteRequest(article, 1L, user.getId());

        assertThatThrownBy(() -> commentService.delete(request))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("게시글 번호에 해당하지 않는 댓글 번호일 경우 예외를 반환한다.")
    void deleteWhenInvalidRequest() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        CommentResponse comment = commentService.save(LEO.getCommentSaveRequest(article.getId(), user.getId()));
        Article anotherArticle = saveArticle(ArticleFixture.LEO, user);

        CommentDeleteRequest request = EVOLVED_LEO.getCommentDeleteRequest(anotherArticle, comment.getCommentId(), user.getId());

        assertThatThrownBy(() -> commentService.delete(request))
                .isInstanceOf(InvalidRequestException.class);
    }

    @Test
    @DisplayName("댓글 작성자의 요청이 아닌 경우 예외를 반환한다.")
    void deleteWhenForbidden() {
        User user = saveUser(UserFixture.LEO);
        Article article = saveArticle(ArticleFixture.LEO, user);
        User anotherUser = saveUser(UserFixture.MIO);

        CommentResponse given = commentService.save(LEO.getCommentSaveRequest(article.getId(), user.getId()));

        CommentDeleteRequest request = EVOLVED_LEO.getCommentDeleteRequest(article, given.getCommentId(), anotherUser.getId());

        assertThatThrownBy(() -> commentService.delete(request))
                .isInstanceOf(ForbiddenException.class);
    }


    private User saveUser(UserFixture user) {
        return userRepository.save(user.getUser());
    }

    private Article saveArticle(ArticleFixture article, User user) {
        return articleRepository.save(article.getArticle(user));
    }
}
