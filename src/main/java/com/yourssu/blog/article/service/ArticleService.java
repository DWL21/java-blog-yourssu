package com.yourssu.blog.article.service;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.article.model.repository.CommentArticleRepository;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.article.model.repository.ArticleRepository;
import com.yourssu.blog.user.model.repository.UserRepository;
import com.yourssu.blog.article.service.dto.ArticleDeleteRequest;
import com.yourssu.blog.article.service.dto.ArticleResponse;
import com.yourssu.blog.article.service.dto.ArticleSaveRequest;
import com.yourssu.blog.article.service.dto.ArticleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentArticleRepository commentArticleRepository;

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
        if (article.equalsContext(request.getArticle(user))) {
            return ArticleResponse.of(article);
        }
        article.update(request.getArticle(user));
        return ArticleResponse.ofEdited(article);
    }

    public void delete(final ArticleDeleteRequest request) {
        User user = userRepository.get(request.userId());
        Article article = articleRepository.get(request.articleId());
        article.validateUser(user);
        commentArticleRepository.deleteArticleWithComments(article);
    }
}
