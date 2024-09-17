package com.yourssu.blog.user.service;

import com.yourssu.blog.user.model.User;
import com.yourssu.blog.user.model.repository.UserRepository;
import com.yourssu.blog.support.service.ApplicationTest;
import com.yourssu.blog.user.service.dto.TokenIssueRequest;
import com.yourssu.blog.user.service.dto.TokenResponse;
import com.yourssu.blog.user.service.dto.UserSaveRequest;
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

    @Autowired
    private UserRepository userRepository;

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

    @Test
    @DisplayName("회원을 삭제한다.")
    void delete() {
        User user = userRepository.save(LEO.getUser());

        assertThatNoException().isThrownBy(
                () -> userService.delete(user.getId())
        );
    }
}