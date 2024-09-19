package com.yourssu.blog.comment.model.repository;

import com.yourssu.blog.comment.exception.CommentNotFoundException;
import com.yourssu.blog.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment get(Long id) {
        return findById(id).orElseThrow(CommentNotFoundException::new);
    }
}
