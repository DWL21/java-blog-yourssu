package com.yourssu.blog.model.repository;

import com.yourssu.blog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    default Comment get(Long id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }
}
