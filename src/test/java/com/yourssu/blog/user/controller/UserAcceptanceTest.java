package com.yourssu.blog.user.controller;

import com.yourssu.blog.user.controller.dto.AuthenticateRequest;
import com.yourssu.blog.user.controller.dto.UserCreateRequest;
import com.yourssu.blog.user.service.dto.TokenResponse;
import com.yourssu.blog.user.service.dto.UserResponse;
import com.yourssu.blog.support.acceptance.AcceptanceTest;
import com.yourssu.blog.support.common.fixture.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.yourssu.blog.support.acceptance.AcceptanceContext.invokeDeleteWithToken;
import static com.yourssu.blog.support.acceptance.AcceptanceContext.invokePost;
import static com.yourssu.blog.support.common.fixture.UserFixture.LEO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SuppressWarnings("NonAsciiCharacters")
public class UserAcceptanceTest extends AcceptanceTest {

    @Test
    void 회원_가입을_요청한다() {
        // Given
        UserCreateRequest request = LEO.getUserCreateRequest();

        // When
        var response = invokePost("/api/users", request);

        //Then
        UserResponse actual = response.as(UserResponse.class);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value()),
                () -> assertThat(actual.getEmail()).isEqualTo(LEO.getEmail())
        );
    }

    @Test
    void 인증_토큰을_요청한다() {
        // Given
        createUser(LEO);

        // When
        var response = invokePost("/api/users/token", LEO.getAuthenticateRequest());

        //Then
        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        );
    }

    @Test
    void 회원_삭제를_요청한다() {
        // Given
        createUser(UserFixture.LEO);
        String token = authenticate(UserFixture.LEO).getToken();

        // When
        var response = invokeDeleteWithToken("/api/users", token);

        assertAll(
                () -> assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value())
        );
    }

    public static UserResponse createUser(UserFixture user) {
        UserCreateRequest request = user.getUserCreateRequest();
        return invokePost("/api/users", request).as(UserResponse.class);
    }

    public static TokenResponse authenticate(UserFixture user) {
        AuthenticateRequest request = user.getAuthenticateRequest();
        return invokePost("/api/users/token", request).as(TokenResponse.class);
    }
}
