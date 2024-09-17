package com.yourssu.blog.service;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.model.User;
import com.yourssu.blog.model.repository.ArticleRepository;
import com.yourssu.blog.model.repository.CommentRepository;
import com.yourssu.blog.model.repository.UserRepository;
import com.yourssu.blog.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public CommentResponse findCommentById(final CommentRequest request) {
        Article article = articleRepository.get(request.articleId());
        Comment comment = commentRepository.get(request.commentId());
        comment.validateArticle(article);
        return CommentResponse.of(comment);
    }

    public CommentResponse save(final CommentSaveRequest request) {
        User user = userRepository.get(request.userId());
        Article article = articleRepository.get(request.articleId());
        Comment comment = commentRepository.save(request.getComment(article, user));
        return CommentResponse.of(comment);
    }

    public CommentResponse update(final CommentUpdateRequest request) {
        User user = userRepository.get(request.userId());
        Article article = articleRepository.get(request.articleId());
        Comment comment = commentRepository.get(request.commentId());
        comment.update(request.getComment(article, user));
        return CommentResponse.of(comment);
    }

    public void delete(CommentDeleteRequest request) {
        User user = userRepository.get(request.userId());
        Article article = articleRepository.get(request.articleId());
        Comment comment = commentRepository.get(request.commentId());
        comment.validateUser(user);
        comment.validateArticle(article);
        commentRepository.delete(comment);
    }
}
