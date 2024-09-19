package com.yourssu.blog.comment.service.dto;

import com.yourssu.blog.comment.model.Comment;
import com.yourssu.blog.user.model.User;
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
    private Boolean isEdited;

    public CommentResponse(
            final Long commentId,
            final String email,
            final String content) {
        this(commentId, email, content, Boolean.FALSE);
    }

    private CommentResponse(final Long commentId,
                            final String email,
                            final String content,
                            final Boolean isEdited) {
        this.commentId = commentId;
        this.email = email;
        this.content = content;
        this.isEdited = isEdited;
    }

    public static CommentResponse of(final Comment comment) {
        User user = comment.getUser();
        return new CommentResponse(
                comment.getId(),
                user.getEmail(),
                comment.getContent());
    }

    public static CommentResponse ofEdited(final Comment comment) {
        User user = comment.getUser();
        return new CommentResponse(
                comment.getId(),
                user.getEmail(),
                comment.getContent(),
                true);
    }
}
