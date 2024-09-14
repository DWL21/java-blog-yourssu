package com.yourssu.blog.service;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.repository.ArticleRepository;
import com.yourssu.blog.service.dto.ArticleResponse;
import com.yourssu.blog.service.dto.ArticleSaveRequest;
import com.yourssu.blog.service.dto.ArticleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public ArticleResponse findArticleById(final Long id) {
        Article article = articleRepository.get(id);
        return ArticleResponse.of(article);
    }

    @Transactional
    public ArticleResponse saveArticle(final ArticleSaveRequest request) {
        Article article = request.getArticle();
        Article savedArticle = articleRepository.save(article);
        return ArticleResponse.of(savedArticle);
    }

    @Transactional
    public ArticleResponse update(final ArticleUpdateRequest request) {
        Article article = request.getArticle();
        Article updatedArticle = articleRepository.get(article.getArticleId());
        updatedArticle.update(article);
        return ArticleResponse.of(updatedArticle);
    }
}
