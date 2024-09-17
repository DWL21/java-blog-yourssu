package com.yourssu.blog.user.model.repository;

import com.yourssu.blog.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("delete from Comment c where c.user = :user")
    void deleteComment(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("delete from Comment c where c.article in (select a from Article a where a.user = :user)")
    void deleteCommentsByArticles(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("delete from Article a where a.user = :user")
    void deleteArticlesByUser(@Param("user") User user);

    @Transactional
    @Modifying
    @Query("delete from User u where u = :user")
    void deleteUser(@Param("user") User user);

    @Transactional
    @Modifying
    default void deleteUserArticlesAndComments(User user) {
        deleteComment(user);
        deleteCommentsByArticles(user);
        deleteArticlesByUser(user);
        deleteUser(user);
    }

    default User get(Long id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }

    default User getByEmail(String email) {
        return findByEmail(email).orElseThrow(RuntimeException::new);
    }
}
