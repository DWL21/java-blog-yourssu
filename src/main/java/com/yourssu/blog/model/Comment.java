package com.yourssu.blog.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String content;

    public Comment(Article article, User user, String content) {
        this(null, article, user, content);
    }

    public Comment(Long commentId, Article article, User user, String content) {
        this.commentId = commentId;
        this.article = article;
        this.user = user;
        this.content = content;
    }

    public void update(Comment comment) {
        validateUser(comment.user);
        validateArticle(comment.article);
        this.content = comment.content;
    }

    public void validateUser(User user) {
        if (!this.user.equals(user)) {
            throw new IllegalArgumentException("Article does not belong to user");
        }
    }

    public void validateArticle(Article article) {
        if (!article.equals(this.article)) {
            throw new IllegalArgumentException("Article does not belong to article");
        }
    }
}
