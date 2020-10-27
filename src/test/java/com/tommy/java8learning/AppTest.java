package com.tommy.java8learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {

    @Test
    @DisplayName("임의 객체의 인스턴스 메소드 참조 레퍼런스")
    void compare() {
        String[] names = {"keesun", "whiteship", "toby"};
        Arrays.sort(names, String::compareToIgnoreCase);
        assertThat(names).containsExactly("keesun", "toby", "whiteship");
    }
}
