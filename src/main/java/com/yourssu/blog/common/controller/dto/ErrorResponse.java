package com.yourssu.blog.common.controller.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final LocalDateTime time;
    private final String message;

    public ErrorResponse(String message) {
        this.time = LocalDateTime.now();
        this.message = message;
    }
}
