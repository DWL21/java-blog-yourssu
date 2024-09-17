package com.yourssu.blog.model;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "article")
@Getter
public class Article extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public Article(User user, String title, String content) {
        this(null, user, title, content);
    }

    public Article(Long id, User user, String title, String content) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(Article article) {
        validateUser(article.user);
        this.title = article.title;
        this.content = article.content;
    }

    public void validateUser(User user) {
        if (!this.user.equals(user)) {
            throw new IllegalArgumentException("Article does not belong to user");
        }
    }
}
