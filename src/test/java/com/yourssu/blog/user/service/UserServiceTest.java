package com.yourssu.blog.user.service;

import com.yourssu.blog.common.exception.InvalidRequestException;
import com.yourssu.blog.user.exception.ExistsUserException;
import com.yourssu.blog.user.exception.UserNotFoundException;
import com.yourssu.blog.user.model.User;
import com.yourssu.blog.user.model.repository.UserRepository;
import com.yourssu.blog.support.service.ApplicationTest;
import com.yourssu.blog.user.service.dto.TokenIssueRequest;
import com.yourssu.blog.user.service.dto.TokenResponse;
import com.yourssu.blog.user.service.dto.UserSaveRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.yourssu.blog.support.common.fixture.UserFixture.*;
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
    @DisplayName("이메일이 올바르지 않은 형식일 경우 예외를 반환한다.")
    void saveWhenInvalidEmail() {
        UserSaveRequest request = LEO_EMAIL_INCORRECT.getUserSaveRequest();

        assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(InvalidRequestException.class);
    }

    @Test
    @DisplayName("이메일이 이미 회원가입 되었을 경우 예외를 반환한다.")
    void saveWhenExistsUser() {
        UserSaveRequest request = LEO.getUserSaveRequest();
        userService.save(request);

        assertThatThrownBy(() -> userService.save(request))
                .isInstanceOf(ExistsUserException.class);
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
    @DisplayName("입력한 이메일에 해당하는 회원이 존재하지 않은 경우 예외를 반환한다.")
    void issueTokenNotFoundUser() {
        TokenIssueRequest request = LEO.getTokenIssueRequest();

        assertThatThrownBy(() -> userService.issueToken(request))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    @DisplayName("비밀번호가 일치하지 않은 경우 예외를 발생시킨다.")
    void issueTokenWhenInvalidPassword() {
        userService.save(LEO.getUserSaveRequest());

        TokenIssueRequest request = LEO_PASSWORD_INCORRECT.getTokenIssueRequest();

        assertThatThrownBy(
                () -> userService.issueToken(request))
                .isInstanceOf(InvalidRequestException.class);
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