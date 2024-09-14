package com.yourssu.blog.model.fixture;

import lombok.Getter;

@Getter
public enum UserFixture {

    LEO("leo@yourssu.com",
            "leo",
            "leoPassword"
    );

    private final String email;
    private final String name;
    private final String password;

    UserFixture(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
