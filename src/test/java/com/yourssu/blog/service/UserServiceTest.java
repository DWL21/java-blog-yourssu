package com.yourssu.blog.service;

import com.yourssu.blog.service.dto.TokenIssueRequest;
import com.yourssu.blog.service.dto.UserSaveRequest;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.UserFixture.LEO;
import static org.assertj.core.api.Assertions.assertThatNoException;

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

        assertThatNoException().isThrownBy(
                () -> userService.issueToken(request)
        );
    }
}