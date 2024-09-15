package com.yourssu.blog.support.common.fixture;

import com.yourssu.blog.controller.dto.CommentCreateRequest;
import com.yourssu.blog.model.Article;
import com.yourssu.blog.model.Comment;
import com.yourssu.blog.service.dto.CommentSaveRequest;

public enum CommentFixture {

    LEO(UserFixture.LEO,
            "leo comment");

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
}
