package com.yourssu.blog.user.model;

import com.yourssu.blog.common.exception.InvalidRequestException;
import com.yourssu.blog.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "\"user\"")
@Getter
public class User extends BaseEntity {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "passsword", nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    public User(String email, String password, String username) {
        this(null, email, password, username);
    }

    public User(Long id, String email, String password, String username) {
        isValidEmail(email);
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    private void isValidEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidRequestException("올바르지 않은 이메일 형식입니다.");
        }
    }

    public void validatePassword(String password) {
        if (!this.password.equals(password)) {
            throw new InvalidRequestException("비밀번호가 일치하지 않습니다.");
        }
    }
}
