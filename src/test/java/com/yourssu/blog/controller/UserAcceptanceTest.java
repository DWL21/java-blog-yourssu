package com.yourssu.blog.controller;

import com.yourssu.blog.controller.dto.AuthenticateRequest;
import com.yourssu.blog.controller.dto.UserCreateRequest;
import com.yourssu.blog.service.dto.TokenResponse;
import com.yourssu.blog.service.dto.UserResponse;
import com.yourssu.blog.support.acceptance.AcceptanceTest;
import com.yourssu.blog.support.common.fixture.UserFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

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

    public static UserResponse createUser(UserFixture user) {
        UserCreateRequest request = user.getUserCreateRequest();
        return invokePost("/api/users", request).as(UserResponse.class);
    }

    public static TokenResponse authenticate(UserFixture user) {
        AuthenticateRequest request = user.getAuthenticateRequest();
        return invokePost("/api/users/token", request).as(TokenResponse.class);
    }
}
