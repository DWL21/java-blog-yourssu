package com.yourssu.blog.user.controller;

import com.yourssu.blog.common.config.support.JwtTokenProvider;
import com.yourssu.blog.common.exception.ForbiddenException;
import com.yourssu.blog.common.exception.UnauthenticatedException;
import com.yourssu.blog.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(LoginUserId.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        String token = webRequest.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new UnauthenticatedException("Authorization 헤더가 비어 있습니다.");
        }
        try {
            return Long.valueOf(jwtTokenProvider.extractAllClaims(token).getSubject());
        } catch (Exception e) {
            throw new ForbiddenException("유효하지 않은 회원 인증 정보입니다.");
        }
    }
}
