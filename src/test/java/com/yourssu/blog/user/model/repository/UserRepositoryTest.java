package com.yourssu.blog.user.model.repository;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.article.model.repository.ArticleRepository;
import com.yourssu.blog.article.service.ArticleService;
import com.yourssu.blog.comment.model.Comment;
import com.yourssu.blog.comment.model.repository.CommentRepository;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.common.fixture.CommentFixture;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.support.common.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("회원 번호와 일치하는 회원을 반환한다.")
    void findById() {
        UserFixture user = UserFixture.LEO;
        User expected = userRepository.save(user.getUser());

        User actual = userRepository.get(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("이메일과 일치하는 회원을 반환한다.")
    void findByEmail() {
        UserFixture user = UserFixture.LEO;
        userRepository.save(user.getUser());

        assertThatNoException().isThrownBy(
                () -> userRepository.getByEmail(user.getEmail())
        );
    }

    @Test
    @DisplayName("회원이 작성한 게시글과 댓글을 삭제한다.")
    void deleteArticlesWithComments() {
        User user = userRepository.save(UserFixture.LEO.getUser());
        Article article1 = articleRepository.save(ArticleFixture.LEO.getArticle(user));
        Comment comment1 = commentRepository.save(CommentFixture.LEO.getComment(article1, user));
        Article article2 = articleRepository.save(ArticleFixture.LEO.getArticle(user));
        Comment comment2 = commentRepository.save(CommentFixture.LEO.getComment(article2, user));

        userRepository.deleteUserArticlesAndComments(user);
        Boolean existsArticle1 = articleRepository.existsById(article1.getId());
        Boolean existsArticle2 = articleRepository.existsById(article2.getId());
        Boolean existsComment1 = commentRepository.existsById(comment1.getId());
        Boolean existsComment2 = commentRepository.existsById(comment2.getId());
        Boolean existsUser = userRepository.existsById(user.getId());

        assertAll(
                () -> assertThat(existsArticle1).isFalse(),
                () -> assertThat(existsArticle2).isFalse(),
                () -> assertThat(existsComment1).isFalse(),
                () -> assertThat(existsComment2).isFalse(),
                () -> assertThat(existsUser).isFalse()
        );
    }
}