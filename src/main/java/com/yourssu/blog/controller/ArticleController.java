package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.controller.dto.ArticleEditRequest;
import com.yourssu.blog.service.ArticleService;
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
    public ResponseEntity<ArticleResponse> save(@RequestBody ArticleCreateRequest request) {
        ArticleResponse article = articleService.saveArticle(request.toArticleSaveRequest());
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> edit(@PathVariable Long articleId, @RequestBody ArticleEditRequest request) {
        ArticleResponse article = articleService.update(request.toArticleUpdatedRequest(articleId));
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }
}
