package com.yourssu.blog.comment.service;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.comment.model.Comment;
import com.yourssu.blog.comment.service.dto.*;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.article.model.repository.ArticleRepository;
import com.yourssu.blog.comment.model.repository.CommentRepository;
import com.yourssu.blog.user.model.repository.UserRepository;
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
        if (comment.equalsContent(request.getComment(article, user))) {
            CommentResponse.of(comment);
        }
        comment.update(request.getComment(article, user));
        return CommentResponse.ofEdited(comment);
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
