package com.yourssu.blog.user.service;

import com.yourssu.blog.common.config.support.Encrypt;
import com.yourssu.blog.common.config.support.JwtTokenProvider;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.user.model.repository.UserRepository;
import com.yourssu.blog.user.service.dto.TokenIssueRequest;
import com.yourssu.blog.user.service.dto.TokenResponse;
import com.yourssu.blog.user.service.dto.UserResponse;
import com.yourssu.blog.user.service.dto.UserSaveRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtTokenProvider jwtTokenProvider;
    private final Encrypt encrypt;


    public UserResponse save(UserSaveRequest request) {
        User user = new User(request.email(), encrypt.encrypt(request.password()), request.username());
        userRepository.save(user);
        return UserResponse.of(user);
    }

    public TokenResponse issueToken(TokenIssueRequest request) {
        User user = userRepository.getByEmail(request.email());
        user.validatePassword(encrypt.encrypt(request.password()));
        return TokenResponse.of(generateToken(user));
    }

    private String generateToken(User user) {
        Claims claims = Jwts.claims()
                .subject(String.valueOf(user.getId()))
                .add("email", user.getEmail())
                .add("username", user.getUsername())
                .build();
        return jwtTokenProvider.generateToken(claims);
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
