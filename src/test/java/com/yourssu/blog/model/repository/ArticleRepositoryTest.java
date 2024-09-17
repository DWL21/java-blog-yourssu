package com.yourssu.blog.model.repository;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.User;
import com.yourssu.blog.support.common.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.yourssu.blog.support.common.fixture.ArticleFixture.LEO;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("게시글 번호에 맞는 게시글을 반환한다.")
    void findById() {
        User user = userRepository.save(UserFixture.LEO.getUser());
        Article expected = articleRepository.save(LEO.getArticle(user));

        Article actual = articleRepository.get(expected.getArticleId());

        assertThat(actual).isEqualTo(expected);
    }
}