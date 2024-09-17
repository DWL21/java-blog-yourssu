package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.controller.dto.CommentCreateRequest;
import com.yourssu.blog.controller.dto.CommentEditRequest;
import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.model.User;
import com.yourssu.blog.service.dto.CommentDeleteRequest;
import com.yourssu.blog.service.dto.CommentRequest;
import com.yourssu.blog.service.dto.CommentSaveRequest;
import com.yourssu.blog.service.dto.CommentUpdateRequest;

public enum CommentFixture {

    LEO("leo comment"),
    EVOLVED_LEO("leo evolved comment");

    private final String content;

    CommentFixture(String content) {
        this.content = content;
    }

    public Comment getComment(Article article, User user) {
        return new Comment(article, user, content);
    }

    public CommentCreateRequest getCommentCreateRequest() {
        return new CommentCreateRequest(content);
    }

    public CommentSaveRequest getCommentSaveRequest(Article article, Long userId) {
        return new CommentSaveRequest(article.getId(), userId, content);
    }

    public CommentEditRequest getCommentEditRequest() {
        return new CommentEditRequest(content);
    }

    public CommentUpdateRequest getCommentUpdateRequest(Article article, Long commentId, Long userId) {
        return new CommentUpdateRequest(
                new CommentRequest(article.getId(), commentId),
                userId,
                content);
    }

    public CommentDeleteRequest getCommentDeleteRequest(Article article, Long commentId, Long userId) {
        return new CommentDeleteRequest(new CommentRequest(article.getId(), commentId), userId);
    }
}
