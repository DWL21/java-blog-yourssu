package com.yourssu.blog.model.repository;

import com.yourssu.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    default Article get(Long id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }
}
