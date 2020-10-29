package com.tommy.java8learning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

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

        while (stringSpliterator.tryAdvance(System.out::println)) ;
        System.out.println("======");
        while (stringSpliterator1.tryAdvance(System.out::println)) ;
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

    @Test
    @DisplayName("stream Api를 이용하여 대문자로 변환한 리스트를 반환")
    void streamUpperCase() {
        List<String> results = names.stream()
                                   .map(String::toUpperCase)
                                   .collect(Collectors.toList());
        assertThat(results).containsExactly("KEESUN", "HANGYEOL", "WHITESHIP","TOBY", "TOMMY", "FOO");
    }

    @Test
    @DisplayName("stream Api의 병렬 처리")
    @Description("ParallelStream 으로 병렬 처리를 한다고 무조건 빨라지는 것이 아니다." +
                 "데이터가 정말 방대할 경우 ParallelStream 가 유용하다.")
    void parallelStream() {
        List<String> parallelResults = names.parallelStream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.toList());
        assertThat(parallelResults).containsExactly("KEESUN", "HANGYEOL", "WHITESHIP","TOBY", "TOMMY", "FOO");
    }

}
