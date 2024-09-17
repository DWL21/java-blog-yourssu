package com.yourssu.blog.article.model.repository;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.comment.model.Comment;
import com.yourssu.blog.comment.model.repository.CommentRepository;
import com.yourssu.blog.support.common.fixture.CommentFixture;
import com.yourssu.blog.support.common.fixture.UserFixture;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.user.model.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.yourssu.blog.support.common.fixture.ArticleFixture.LEO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class CommentArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentArticleRepository commentArticleRepository;

    @Test
    @DisplayName("게시글에 속한 모든 댓글을 삭제한다.")
    void deleteArticleWithComments() {
        User user = userRepository.save(UserFixture.LEO.getUser());
        Article article = articleRepository.save(LEO.getArticle(user));
        Comment comment1 = commentRepository.save(CommentFixture.LEO.getComment(article, user));
        Comment comment2 = commentRepository.save(CommentFixture.LEO.getComment(article, user));

        commentArticleRepository.deleteArticleWithComments(article);
        Boolean existsArticle = articleRepository.existsById(article.getId());
        Boolean existsComment1 = commentArticleRepository.existsById(comment1.getId());
        Boolean existsComment2 = commentArticleRepository.existsById(comment2.getId());

        assertAll(
                () -> assertThat(existsArticle).isFalse(),
                () -> assertThat(existsComment1).isFalse(),
                () -> assertThat(existsComment2).isFalse()
        );
    }
}