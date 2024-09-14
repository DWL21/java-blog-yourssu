package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Article;
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

    public ArticleResponse(final Long articleId,
                           final String email,
                           final String title,
                           final String content) {
        this.articleId = articleId;
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public static ArticleResponse of(final Article article) {
        return new ArticleResponse(
                article.getArticleId(),
                article.getEmail(),
                article.getTitle(),
                article.getContent());
    }
}