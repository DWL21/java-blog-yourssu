package com.yourssu.blog.model;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Article(String email, String title, String content) {
        this(null, email, title, content);
    }

    public Article(Long articleId, String email, String title, String content) {
        this.articleId = articleId;
        this.email = email;
        this.title = title;
        this.content = content;
    }

    public void update(Article article) {
        this.title = article.title;
        this.content = article.content;
    }
}
