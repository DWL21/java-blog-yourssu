package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.AuthenticateRequest;
import com.yourssu.blog.controller.dto.UserCreateRequest;
import com.yourssu.blog.service.UserService;
import com.yourssu.blog.service.dto.TokenResponse;
import com.yourssu.blog.service.dto.UserResponse;
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
    public ResponseEntity<UserResponse> create(@RequestBody UserCreateRequest request) {
        UserResponse response = userService.save(request.toUserSaveRequest());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthenticateRequest request) {
        TokenResponse response = userService.issueToken(request.toTokenIssueRequest());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping()
    public ResponseEntity<Void> remove(@LoginUserId Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
