package com.tommy.java8learning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

class GreetingTest {

    @Test
    @DisplayName("Greeting - hi static 메서드 레퍼런스 기능")
    void hi() {
        UnaryOperator<String> hi = Greeting::hi;
        assertThat(hi.apply("hangyeol")).isEqualTo("hi hangyeol");
    }

    @Test
    @DisplayName("Greeting - hello instance 메서드 레퍼런스 기능")
    void hello() {
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello;
        assertThat(hello.apply("hangyeol")).isEqualTo("hello hangyeol");
    }

    @Test
    @DisplayName("Greeting - Default 생성자 초기화 메서드 레퍼런스 기능")
    void defaultConstruct() {
        Supplier<Greeting> newGreeting = Greeting::new;
        assertThat(newGreeting.get().hello("hangyeol")).isEqualTo("hello hangyeol");
    }

    @Test
    @DisplayName("Greeting - 생성자 초기화 메서드 레퍼런스 기능")
    void construct() {
        Function<String, Greeting> hangyeolGreeting = Greeting::new;
        Greeting hangyeol = hangyeolGreeting.apply("hangyeol");
        String name = hangyeol.getName();
        assertThat(hangyeol.hello(name)).isEqualTo("hello hangyeol");
    }
}
