package com.yourssu.blog.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String content;

    public Comment(Article article, String email, String content) {
        this(null, article, email, content);
    }

    public Comment(Long commentId, Article article, String email, String content) {
        this.commentId = commentId;
        this.article = article;
        this.email = email;
        this.content = content;
    }

    public void update(Comment comment) {
        this.content = comment.content;
    }
}
