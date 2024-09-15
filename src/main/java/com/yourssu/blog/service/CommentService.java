package com.yourssu.blog.service;

import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.model.repository.ArticleRepository;
import com.yourssu.blog.model.repository.CommentRepository;
import com.yourssu.blog.service.dto.CommentRequest;
import com.yourssu.blog.service.dto.CommentResponse;
import com.yourssu.blog.service.dto.CommentSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public CommentResponse findCommentById(final CommentRequest commentRequest) {
        Comment comment = commentRepository.get(commentRequest.commentId());
        return CommentResponse.of(comment);
    }

    public CommentResponse save(final CommentSaveRequest request) {
        Article article = articleRepository.get(request.articleId());
        Comment comment = commentRepository.save(request.getComment(article));
        return CommentResponse.of(comment);
    }
}
