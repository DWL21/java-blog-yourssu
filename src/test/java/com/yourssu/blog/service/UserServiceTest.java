package com.yourssu.blog.service;

import com.yourssu.blog.service.dto.TokenIssueRequest;
import com.yourssu.blog.service.dto.TokenResponse;
import com.yourssu.blog.service.dto.UserSaveRequest;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.UserFixture.LEO;
import static com.yourssu.blog.support.common.fixture.UserFixture.LEO_PASSWORD_INCORRECT;
import static org.assertj.core.api.Assertions.*;

@ApplicationTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("회원을 생성한다.")
    void save() {
        UserSaveRequest request = LEO.getUserSaveRequest();

        assertThatNoException().isThrownBy(
                () -> userService.save(request)
        );
    }

    @Test
    @DisplayName("토큰을 발급한다.")
    void issueToken() {
        userService.save(LEO.getUserSaveRequest());
        TokenIssueRequest request = LEO.getTokenIssueRequest();

        TokenResponse response = userService.issueToken(request);

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않으면 예외를 발생시킨다.")
    void issueToken_invalidPassword() {
        userService.save(LEO.getUserSaveRequest());

        TokenIssueRequest request = LEO_PASSWORD_INCORRECT.getTokenIssueRequest();

        assertThatThrownBy(
                () -> userService.issueToken(request))
                .isInstanceOf(IllegalArgumentException.class);
    }
}