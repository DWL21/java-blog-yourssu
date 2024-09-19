package com.yourssu.blog.article.service.dto;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.user.model.User;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.Getter;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ArticleResponse implements Serializable {

    private Long articleId;
    private String email;
    private String title;
    private String content;
    private Boolean isEdited;

    public ArticleResponse(Long articleId, String email, String title, String content) {
        this(articleId, email, title, content, Boolean.FALSE);
    }

    private ArticleResponse(final Long articleId,
                            final String email,
                            final String title,
                            final String content,
                            final Boolean isEdited) {
        this.articleId = articleId;
        this.email = email;
        this.title = title;
        this.content = content;
        this.isEdited = isEdited;
    }

    public static ArticleResponse of(final Article article) {
        User user = article.getUser();
        return new ArticleResponse(
                article.getId(),
                user.getEmail(),
                article.getTitle(),
                article.getContent());
    }

    public static ArticleResponse of(final Article article, final boolean isEdited) {
        User user = article.getUser();
        return new ArticleResponse(
                article.getId(),
                user.getEmail(),
                article.getTitle(),
                article.getContent(),
                isEdited);
    }
}