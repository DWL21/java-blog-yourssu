package com.yourssu.blog.article.model.repository;

import com.yourssu.blog.article.model.Article;
import com.yourssu.blog.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentArticleRepository extends JpaRepository<Comment, Long> {

    @Transactional
    @Modifying
    @Query("delete from Comment c where c.article = :article")
    void deleteAllByArticle(@Param("article") Article article);

    @Transactional
    @Modifying
    @Query("delete from Article a where a = :article")
    void deleteArticle(@Param("article") Article article);

    @Transactional
    @Modifying
    default void deleteArticleWithComments(Article article) {
        deleteAllByArticle(article);
        deleteArticle(article);
    }
}
