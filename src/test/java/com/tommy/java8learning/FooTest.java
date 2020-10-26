package com.tommy.java8learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.*;

import static org.assertj.core.api.Assertions.assertThat;

class FooTest {

    private Function<Integer, Integer> plus10;
    private Function<Integer, Integer> multiply2;
    private Function<Integer, Integer> multiply2AndPlus10;

    @BeforeEach
    void setUp() {
        plus10 = (number) -> number + 10;
        multiply2 = (number) -> number * 2;
        multiply2AndPlus10 = plus10.compose(multiply2);
    }

    @Test
    void functionalInterface() {
        RunSomething runSomething = (number) -> number + 10;
        assertThat(runSomething.doIt(10)).isEqualTo(20);
    }

    @Test
    @DisplayName("클래스를 구현하여 Function 함수 기능 구현")
    void plus10Test1() {
        Plus10 plus10 = new Plus10();
        assertThat(plus10.apply(10)).isEqualTo(20);
    }

    @Test
    @DisplayName("람다 표현식을 이용하여 number + 10 Function 함수 기능 구현")
    void plus10Test2() {
        assertThat(plus10.apply(10)).isEqualTo(20);
    }

    @Test
    @DisplayName("람다 표현식을 이용하여 number * 2 Function 함수 기능 구현")
    void multiply2Test() {
        assertThat(multiply2.apply(3)).isEqualTo(6);
    }

    @Test
    @DisplayName("함수 계산 앞에 함수를 조합하는 compose 기능")
    void multiply2AndPlus10Test() {
        assertThat(multiply2AndPlus10.apply(2)).isEqualTo(14);
    }

    @Test
    @DisplayName("함수 계산 뒤에 함수를 조합하는 andThen 기능")
    void plus10AndMultiplyTest() {
        assertThat(plus10.andThen(multiply2).apply(2)).isEqualTo(24);
    }

    @Test
    @DisplayName("입력값을 그래도 리턴해주는 Consumer 기능")
    void consumerTest() {
        Consumer<Integer> printType = System.out::println;
        printType.accept(10);
    }

    @Test
    @DisplayName("받아올 값의 타입을 정의하는 Supplier 기능")
    void supplierTest() {
        Supplier<Integer> get10 =  () -> 10;
        assertThat(get10.get()).isEqualTo(10);
    }

    @Test
    @DisplayName("입력받은 인자가 True/False 인지 판결하는 인터페이스")
    void predicateTest1() {
        Predicate<String> startsWithHangyeol = (input) -> input.startsWith("Hangyeol");
        assertThat(startsWithHangyeol.test("Hangyeol")).isTrue();
    }

    @Test
    @DisplayName("입력받은 인자가 True/False 인지 판결하는 인터페이스")
    void predicateTest2() {
        Predicate<Integer> isEven = (number) -> number % 2 == 0;
        assertThat(isEven.test(2)).isTrue();
    }

    @Test
    @DisplayName("입력값과 결과값의 타입이 같을 경우 UnaryOperator로 축약 가능 (Function 상속)")
    void unaryOperatorTest() {
        UnaryOperator<Integer> plus20 = (number) -> number + 20;
        assertThat(plus20.apply(15)).isEqualTo(35);
    }

    @Test
    @DisplayName("입력값과 결과값의 타입이 같을 경우 BinaryOperator로 축약 가능 (BiFunction 상속)")
    void binaryOperatorTest() {
        BinaryOperator<Integer> plus = Integer::sum;
        assertThat(plus.apply(15, 20)).isEqualTo(35);
    }
}
