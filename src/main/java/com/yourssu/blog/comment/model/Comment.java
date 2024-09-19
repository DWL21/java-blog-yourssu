package com.yourssu.blog.comment.model;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.common.exception.ForbiddenException;
import com.yourssu.blog.common.exception.InvalidRequestException;
import com.yourssu.blog.common.model.BaseEntity;
import com.yourssu.blog.user.model.User;
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
    @Column(name = "comment_id")
    private Long id;

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

    public Comment(Long id, Article article, User user, String content) {
        this.id = id;
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
            throw new ForbiddenException("댓글 작성자의 요청이 아닙니다.");
        }
    }

    public void validateArticle(Article article) {
        if (!article.equals(this.article)) {
            throw new InvalidRequestException("요청한 게시글 번호에 해당하는 댓글이 아닙니다.");
        }
    }

    public boolean equalsContent(Comment comment) {
        return this.content.equals(comment.content);
    }
}
