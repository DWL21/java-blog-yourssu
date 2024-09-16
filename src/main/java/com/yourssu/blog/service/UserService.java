package com.yourssu.blog.service;

import com.yourssu.blog.config.support.JwtTokenProvider;
import com.yourssu.blog.model.User;
import com.yourssu.blog.model.repository.UserRepository;
import com.yourssu.blog.service.dto.TokenIssueRequest;
import com.yourssu.blog.service.dto.TokenResponse;
import com.yourssu.blog.service.dto.UserResponse;
import com.yourssu.blog.service.dto.UserSaveRequest;
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


    public UserResponse save(UserSaveRequest userSaveRequest) {
        User user = userRepository.save(userSaveRequest.toUser());
        return UserResponse.of(user);
    }

    public TokenResponse issueToken(TokenIssueRequest request) {
        User user = userRepository.getByEmail(request.email());
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
}
