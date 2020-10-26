package com.tommy.java8learning;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FooTest {

    @Test
    void functionalInterface() {
        RunSomething runSomething = (number) -> number + 10;
        assertThat(runSomething.doIt(10)).isEqualTo(20);
    }
}
