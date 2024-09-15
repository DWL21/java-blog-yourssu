package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.controller.dto.CommentCreateRequest;
import com.yourssu.blog.controller.dto.CommentEditRequest;
import com.yourssu.blog.controller.dto.CommentRemoveRequest;
import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.service.dto.CommentDeleteRequest;
import com.yourssu.blog.service.dto.CommentRequest;
import com.yourssu.blog.service.dto.CommentSaveRequest;
import com.yourssu.blog.service.dto.CommentUpdateRequest;

public enum CommentFixture {

    LEO(UserFixture.LEO,
            "leo comment"),
    EVOLVED_LEO(UserFixture.LEO,
            "leo evolved comment");

    private final UserFixture userFixture;
    private final String content;

    CommentFixture(UserFixture userFixture, String content) {
        this.userFixture = userFixture;
        this.content = content;
    }

    public Comment getComment(Article article) {
        return new Comment(article, userFixture.getEmail(), content);
    }

    public CommentCreateRequest getCommentCreateRequest() {
        return new CommentCreateRequest(userFixture.getEmail(), userFixture.getPassword(), content);
    }

    public CommentSaveRequest getCommentSaveRequest(Article article) {
        return new CommentSaveRequest(article.getArticleId(), userFixture.getEmail(), userFixture.getPassword(), content);
    }

    public CommentEditRequest getCommentEditRequest() {
        return new CommentEditRequest(userFixture.getEmail(), userFixture.getPassword(), content);
    }

    public CommentUpdateRequest getCommentUpdateRequest(Article article, Long commentId) {
        return new CommentUpdateRequest(
                new CommentRequest(article.getArticleId(), commentId),
                userFixture.getEmail(),
                userFixture.getPassword(),
                content);
    }

    public CommentRemoveRequest getCommentRemoveRequest() {
        return new CommentRemoveRequest(userFixture.getEmail(), userFixture.getPassword());
    }

    public CommentDeleteRequest getCommentDeleteRequest(Article article, Long commentId) {
        return new CommentDeleteRequest(
                new CommentRequest(article.getArticleId(), commentId),
                userFixture.getEmail(),
                userFixture.getPassword()
        );
    }
}
