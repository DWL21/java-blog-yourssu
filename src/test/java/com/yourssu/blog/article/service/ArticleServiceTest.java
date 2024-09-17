package com.yourssu.blog.article.service;

import com.yourssu.blog.article.service.dto.ArticleDeleteRequest;
import com.yourssu.blog.article.service.dto.ArticleResponse;
import com.yourssu.blog.article.service.dto.ArticleSaveRequest;
import com.yourssu.blog.article.service.dto.ArticleUpdateRequest;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.user.model.repository.UserRepository;
import com.yourssu.blog.support.common.fixture.UserFixture;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.ArticleFixture.*;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ApplicationTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("게시글을 생성한다.")
    void save() {
        User user = saveUser(UserFixture.LEO);
        ArticleSaveRequest request = LEO.getArticleSaveRequest(user.getId());

        assertThatNoException().isThrownBy(
                () -> articleService.save(request)
        );
    }

    @Test
    @DisplayName("게시글을 수정한다.")
    void update() {
        User user = saveUser(UserFixture.LEO);
        ArticleResponse article = articleService.save(LEO.getArticleSaveRequest(user.getId()));

        ArticleUpdateRequest request = EVOLVED_LEO.getArticleUpdateRequest(article.getArticleId(), user.getId());

        assertThatNoException().isThrownBy(
                () -> articleService.update(request)
        );
    }

    @Test
    @DisplayName("게시글을 삭제한다.")
    void delete() {
        User user = saveUser(UserFixture.LEO);
        ArticleResponse given = articleService.save(LEO.getArticleSaveRequest(user.getId()));

        ArticleDeleteRequest request = LEO.getArticleDeleteRequest(given.getArticleId(), user.getId());

        assertThatNoException().isThrownBy(
                () -> articleService.delete(request)
        );
    }

    private User saveUser(UserFixture user) {
        return userRepository.save(user.getUser());
    }
}