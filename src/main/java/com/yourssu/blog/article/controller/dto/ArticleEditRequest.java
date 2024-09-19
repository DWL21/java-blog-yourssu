package com.yourssu.blog.article.controller.dto;

import com.yourssu.blog.article.service.dto.ArticleUpdateRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ArticleEditRequest(
        @NotBlank(message = "제목 입력이 올바르지 않습니다.")
        @Size(max = 255, message = "제목 입력이 255자를 초과할 수 없습니다.")
        String title,

        @NotBlank(message = "본문 입력이 올바르지 않습니다.")
        @Size(max = 255, message = "본문 입력이 255자를 초과할 수 없습니다.")
        String content) {

    public ArticleUpdateRequest toArticleUpdateRequest(Long articleId, Long userId) {
        return new ArticleUpdateRequest(articleId, userId, title, content);
    }
}
