package com.yourssu.blog.model.repository;

import com.yourssu.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    default User get(Long id) {
        return findById(id).orElseThrow(RuntimeException::new);
    }

    default User getByEmail(String email) {
        return findByEmail(email).orElseThrow(RuntimeException::new);
    }
}
