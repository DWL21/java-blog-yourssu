package com.yourssu.blog.service;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.User;
import com.yourssu.blog.model.repository.ArticleRepository;
import com.yourssu.blog.model.repository.UserRepository;
import com.yourssu.blog.service.dto.ArticleDeleteRequest;
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
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public ArticleResponse findArticleById(final Long id) {
        Article article = articleRepository.get(id);
        return ArticleResponse.of(article);
    }

    public ArticleResponse save(final ArticleSaveRequest request) {
        User user = userRepository.get(request.userId());
        Article article = request.getArticle(user);
        Article savedArticle = articleRepository.save(article);
        return ArticleResponse.of(savedArticle);
    }

    public ArticleResponse update(final ArticleUpdateRequest request) {
        User user = userRepository.get(request.userId());
        Article article = articleRepository.get(request.articleId());
        article.update(request.getArticle(user));
        return ArticleResponse.of(article);
    }

    public void delete(final ArticleDeleteRequest request) {
        User user = userRepository.get(request.userId());
        Article article = articleRepository.get(request.articleId());
        article.validateUser(user);
        articleRepository.delete(article);
    }
}
