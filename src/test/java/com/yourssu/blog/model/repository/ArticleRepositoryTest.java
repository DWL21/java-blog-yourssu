package com.yourssu.blog.model.repository;

import com.yourssu.blog.model.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.yourssu.blog.support.common.fixture.ArticleFixture.LEO;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("게시글 번호에 맞는 게시글을 반환한다.")
    void findById() {
        Article article = LEO.getArticle();
        articleRepository.save(article);

        assertThatNoException().isThrownBy(
                () -> articleRepository.get(1L)
        );
    }
}