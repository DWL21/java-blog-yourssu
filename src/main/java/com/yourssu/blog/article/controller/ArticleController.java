package com.yourssu.blog.article.controller;

import com.yourssu.blog.user.controller.LoginUserId;
import com.yourssu.blog.article.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.article.controller.dto.ArticleEditRequest;
import com.yourssu.blog.article.service.ArticleService;
import com.yourssu.blog.article.service.dto.ArticleDeleteRequest;
import com.yourssu.blog.article.service.dto.ArticleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> show(@PathVariable Long articleId) {
        ArticleResponse article = articleService.findArticleById(articleId);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> create(@Valid @RequestBody ArticleCreateRequest request, @LoginUserId Long userId) {
        ArticleResponse article = articleService.save(request.toArticleSaveRequest(userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> edit(@PathVariable Long articleId, @Valid @RequestBody ArticleEditRequest request, @LoginUserId Long userId) {
        ArticleResponse article = articleService.update(request.toArticleUpdateRequest(articleId, userId));
        if (article.getIsEdited()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(article);
        }
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> remove(@PathVariable Long articleId, @LoginUserId Long userId) {
        articleService.delete(new ArticleDeleteRequest(articleId, userId));
        return ResponseEntity.noContent().build();
    }
}
