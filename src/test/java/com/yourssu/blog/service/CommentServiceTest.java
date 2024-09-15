package com.yourssu.blog.service;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.repository.ArticleRepository;
import com.yourssu.blog.service.dto.CommentSaveRequest;
import com.yourssu.blog.support.common.fixture.ArticleFixture;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.CommentFixture.LEO;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ApplicationTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("댓글을 생성한다.")
    void save() {
        Article article = saveArticle(ArticleFixture.LEO);
        CommentSaveRequest request = LEO.getCommentSaveRequest(article);

        assertThatNoException().isThrownBy(
                () -> commentService.save(request)
        );
    }

    private Article saveArticle(ArticleFixture article) {
        return articleRepository.save(article.getArticle());
    }
}
