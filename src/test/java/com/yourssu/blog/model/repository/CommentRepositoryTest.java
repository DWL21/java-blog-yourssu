package com.yourssu.blog.model.repository;

import com.yourssu.blog.article.model.repository.ArticleRepository;
import com.yourssu.blog.comment.model.repository.CommentRepository;
import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.comment.model.Comment;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.common.fixture.UserFixture;
import com.yourssu.blog.user.model.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.yourssu.blog.support.common.fixture.CommentFixture.LEO;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("댓글 번호에 맞는 댓글을 반환한다.")
    void findById() {
        User user = userRepository.save(UserFixture.LEO.getUser());
        Article article = articleRepository.save(ArticleFixture.LEO.getArticle(user));
        Comment comment = LEO.getComment(article, user);
        Comment expected = commentRepository.save(comment);

        Comment actual = commentRepository.get(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }
}
