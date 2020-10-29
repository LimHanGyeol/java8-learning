package com.tommy.java8learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultFooTest {

    private FooInterface fooInterface;

    @Test
    @DisplayName("소문자 이름 출력")
    void printName() {
        fooInterface = new DefaultFoo("hangyeol");
        assertThat(fooInterface.printName()).isEqualTo("hangyeol");
    }

    @Test
    @DisplayName("대문자 이름 출력")
    void printNameUpperCase() {
        fooInterface = new DefaultFoo("hangyeol");
        assertThat(fooInterface.printNameUpperCase()).isEqualTo("HANGYEOL");
    }

    @Test
    @DisplayName("인터페이스의 static 메서드 호출")
    void staticMethod() {
        assertThat(FooInterface.printAnything()).isEqualTo("Foo");
    }

}
