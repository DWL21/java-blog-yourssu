package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.CommentCreateRequest;
import com.yourssu.blog.controller.dto.CommentEditRequest;
import com.yourssu.blog.service.CommentService;
import com.yourssu.blog.service.dto.CommentRequest;
import com.yourssu.blog.service.dto.CommentResponse;
import com.yourssu.blog.service.dto.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles/{articleId}/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> show(@PathVariable Long articleId, @PathVariable Long commentId) {
        CommentResponse comment = commentService.findCommentById(new CommentRequest(articleId, commentId));
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@PathVariable Long articleId, @RequestBody CommentCreateRequest request) {
        CommentResponse comment = commentService.save(request.toCommentSaveRequest(articleId));
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> edit(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody CommentEditRequest request) {
        CommentResponse response = commentService.update(request.toCommentUpdateRequest(articleId, commentId));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
