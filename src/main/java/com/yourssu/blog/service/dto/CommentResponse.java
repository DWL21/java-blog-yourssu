package com.yourssu.blog.service.dto;

import com.yourssu.blog.model.Comment;
import com.yourssu.blog.model.User;
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

    private CommentResponse(final Long commentId,
                           final String email,
                           final String content) {
        this.commentId = commentId;
        this.email = email;
        this.content = content;
    }

    public static CommentResponse of(final Comment comment) {
        User user = comment.getUser();
        return new CommentResponse(
                comment.getId(),
                user.getEmail(),
                comment.getContent());
    }
}
