package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.ArticleCreateRequest;
import com.yourssu.blog.controller.dto.ArticleEditRequest;
import com.yourssu.blog.controller.dto.ArticleRemoveRequest;
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
        ArticleResponse article = articleService.save(request.toArticleSaveRequest());
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> edit(@PathVariable Long articleId, @RequestBody ArticleEditRequest request) {
        ArticleResponse article = articleService.update(request.toArticleUpdateRequest(articleId));
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> remove(@PathVariable Long articleId, @RequestBody ArticleRemoveRequest request) {
        articleService.delete(request.toArticleDeleteRequest(articleId));
        return ResponseEntity.noContent().build();
    }
}
