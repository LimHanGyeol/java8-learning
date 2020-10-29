package com.tommy.java8learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

import static org.assertj.core.api.Assertions.assertThat;

public class Api {

    private List<String> names;

    @BeforeEach
    void setUp() {
        names = new ArrayList<>();
        names.add("keesun");
        names.add("hangyeol");
        names.add("whiteship");
        names.add("toby");
        names.add("tommy");
        names.add("foo");
    }

    @Test
    @DisplayName("forEach 구문으로 리스트 순회")
    void forEach() {
        names.forEach(System.out::println);
        assertThat(names).hasSize(6);
    }

    @Test
    @DisplayName("Spliterator 구문으로 절반을 잘라 순회")
    void spliterator() {
        Spliterator<String> stringSpliterator = names.spliterator();
        Spliterator<String> stringSpliterator1 = stringSpliterator.trySplit();

        while (stringSpliterator.tryAdvance(System.out::println));
        System.out.println("======");
        while (stringSpliterator1.tryAdvance(System.out::println));
    }

    @Test
    @DisplayName("removeIf로 특정 문자를 가진 문자열 제외")
    void removeIf() {
        names.removeIf(word -> word.startsWith("h"));
        names.forEach(System.out::println);
        assertThat(names).hasSize(5);
    }

    @Test
    @DisplayName("내림차순 정렬 reverse")
    void comparator() {
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
        assertThat(names).containsExactly("whiteship", "tommy", "toby", "keesun", "hangyeol", "foo");
    }
    
}
