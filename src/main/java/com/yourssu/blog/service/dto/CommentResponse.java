package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentResponse implements Serializable {

    private Long commentId;
    private String email;
    private String content;

    public CommentResponse(final Long commentId,
                           final String email,
                           final String content) {
        this.commentId = commentId;
        this.email = email;
        this.content = content;
    }

    public static CommentResponse of(final Comment comment) {
        return new CommentResponse(
                comment.getCommentId(),
                comment.getEmail(),
                comment.getContent());
    }
}
