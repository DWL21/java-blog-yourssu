package com.yourssu.blog.model.repository;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.yourssu.blog.support.common.fixture.CommentFixture.LEO;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("댓글 번호에 맞는 댓글을 반환한다.")
    void findById() {
        Article article = articleRepository.save(ArticleFixture.LEO.getArticle());
        Comment comment = LEO.getComment(article);

        commentRepository.save(comment);

        assertThatNoException().isThrownBy(
                () -> commentRepository.get(1L)
        );
    }
}
