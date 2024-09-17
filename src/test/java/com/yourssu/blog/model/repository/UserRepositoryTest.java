package com.yourssu.blog.model.repository;

import com.yourssu.blog.model.User;
import com.yourssu.blog.support.common.fixture.UserFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("회원 번호와 일치하는 회원을 반환한다.")
    void findById() {
        UserFixture user = UserFixture.LEO;
        User expected = userRepository.save(user.getUser());

        User actual = userRepository.get(expected.getId());

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("이메일과 일치하는 회원을 반환한다.")
    void findByEmail() {
        UserFixture user = UserFixture.LEO;
        userRepository.save(user.getUser());

        assertThatNoException().isThrownBy(
                () -> userRepository.getByEmail(user.getEmail())
        );
    }
}