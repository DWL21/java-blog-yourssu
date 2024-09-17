package com.yourssu.blog.config.support;

import com.yourssu.blog.common.config.support.Encrypt;
import com.yourssu.blog.support.service.ApplicationTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@ApplicationTest
class EncryptTest {

    @Autowired
    private Encrypt encrypt;

    @Test
    @DisplayName("문자열을 SHA-256 해시 알고리즘으로 암호화한다.")
    void encrypt() {
        String password = "yourssu2024";

        assertThatNoException().isThrownBy(
                () -> encrypt.encrypt(password));
    }

    @Test
    @Disabled
    @DisplayName("실제로 문자열을 SHA-256 해시 알고리즘으로 암호화한다. Salt가 변경되면 통과하지 않는다.")
    void encryptWithSalt() {
        String password = "yourssu2024";

        String actual = encrypt.encrypt(password);

        String expected = "9b09128cf76b491c12c57c796a87b56f71a207d8ccb2b5911c7c2029c6a6f150";
        assertThat(actual).isEqualTo(expected);
    }
}