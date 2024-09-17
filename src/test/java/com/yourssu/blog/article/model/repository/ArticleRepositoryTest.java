package com.yourssu.blog.article.model.repository;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.support.common.fixture.UserFixture;
import com.yourssu.blog.user.model.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.yourssu.blog.support.common.fixture.ArticleFixture.LEO;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
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

        Article actual = articleRepository.get(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }
}