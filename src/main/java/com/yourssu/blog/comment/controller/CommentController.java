package com.yourssu.blog.comment.controller;

import com.yourssu.blog.comment.controller.dto.CommentCreateRequest;
import com.yourssu.blog.user.controller.LoginUserId;
import com.yourssu.blog.comment.controller.dto.CommentEditRequest;
import com.yourssu.blog.comment.service.CommentService;
import com.yourssu.blog.comment.service.dto.CommentDeleteRequest;
import com.yourssu.blog.comment.service.dto.CommentRequest;
import com.yourssu.blog.comment.service.dto.CommentResponse;
import jakarta.validation.Valid;
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
    public ResponseEntity<CommentResponse> show(
            @PathVariable Long articleId,
            @PathVariable Long commentId) {
        CommentResponse comment = commentService.findCommentById(new CommentRequest(articleId, commentId));
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(
            @PathVariable Long articleId,
            @Valid @RequestBody CommentCreateRequest request,
            @LoginUserId Long userId) {
        CommentResponse comment = commentService.save(request.toCommentSaveRequest(articleId, userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> edit(
            @PathVariable Long articleId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentEditRequest request,
            @LoginUserId Long userId) {
        CommentResponse response = commentService.update(request.toCommentUpdateRequest(articleId, commentId, userId));
        if (response.getIsEdited()) {
            ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long articleId,
            @PathVariable Long commentId,
            @LoginUserId Long userId) {
        commentService.delete(new CommentDeleteRequest(new CommentRequest(articleId, commentId), userId));
        return ResponseEntity.noContent().build();
    }
}
