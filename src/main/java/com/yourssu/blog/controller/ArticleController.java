package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.controller.dto.ArticleEditRequest;
import com.yourssu.blog.service.ArticleService;
import com.yourssu.blog.service.dto.ArticleDeleteRequest;
import com.yourssu.blog.service.dto.ArticleResponse;
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
    public ResponseEntity<ArticleResponse> create(@RequestBody ArticleCreateRequest request, @LoginUserId Long userId) {
        ArticleResponse article = articleService.save(request.toArticleSaveRequest(userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> edit(@PathVariable Long articleId, @RequestBody ArticleEditRequest request, @LoginUserId Long userId) {
        ArticleResponse article = articleService.update(request.toArticleUpdateRequest(articleId, userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> remove(@PathVariable Long articleId, @LoginUserId Long userId) {
        articleService.delete(new ArticleDeleteRequest(articleId, userId));
        return ResponseEntity.noContent().build();
    }
}
