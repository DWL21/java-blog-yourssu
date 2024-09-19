package com.yourssu.blog.article.exception;

import com.yourssu.blog.common.exception.NotFoundException;

public class ArticleNotFoundException extends NotFoundException {

    public ArticleNotFoundException() {
        super("존재하지 않는 게시글입니다.");
    }
}
