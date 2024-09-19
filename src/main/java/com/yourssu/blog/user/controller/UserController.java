package com.yourssu.blog.user.controller;

import com.yourssu.blog.user.controller.dto.AuthenticateRequest;
import com.yourssu.blog.user.controller.dto.UserCreateRequest;
import com.yourssu.blog.user.service.UserService;
import com.yourssu.blog.user.service.dto.TokenResponse;
import com.yourssu.blog.user.service.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserCreateRequest request) {
        UserResponse response = userService.save(request.toUserSaveRequest());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> authenticate(@Valid @RequestBody AuthenticateRequest request) {
        TokenResponse response = userService.issueToken(request.toTokenIssueRequest());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remove(@LoginUserId Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
