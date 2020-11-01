package com.tommy.java8learning.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OnlineClassTest {

    private List<OnlineClass> springClasses;
    private List<OnlineClass> javaClasses;
    private List<List<OnlineClass>> studyEvents;

    @BeforeEach
    void setUp() {
        initSpringClasses();
        initJavaClasses();
        initStudyEvents();
    }

    @Test
    @DisplayName("spring 으로 시작하는 수업")
    void springClassStream() {
        long result = springClasses.stream()
                .filter(onlineClass -> onlineClass.isStartsWithToTitle("spring"))
                .count();
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("close 되지 않은 수업")
    @Description("Java11에서는 .filter(Predicate.not(OnlineClass::isClose))의 형태로도 사용할 수 있음")
    void closeIsFalseClasses() {
        long result = springClasses.stream()
                .filter(onlineClass -> !onlineClass.isClosed())
                .count();
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("수업 이름만 모아서 스트림 만들기")
    void classNameStream() {
        String result = springClasses.stream()
                .map(OnlineClass::getTitle)
                .findFirst()
                .orElse("값이 없습니다.");
        assertThat(result).isEqualTo("spring boot");
    }

    @Test
    @DisplayName("두 수업 목록에 들어있는 모든 수업 아이디 출력")
    void classesToFlatMap() {
        long result = studyEvents.stream()
                .flatMap(Collection::stream)
                .map(OnlineClass::getId)
                .count();
        assertThat(result).isEqualTo(8);
    }

    @Test
    @DisplayName("10부터 1씩 증가하는 무제한 스트림 중에서 앞의 10개 빼고 최대 10개 까지만")
    void limit() {
        List<Integer> results = Stream.iterate(10, number -> number + 1)
                .skip(10)
                .limit(10)
                .collect(Collectors.toList());
        assertThat(results).hasSize(10);
    }

    @Test
    @DisplayName("Java 수업 중에 Test 가 들어있는 수업이 있는지 확인")
    void isContainsTestToJavaStudy() {
        boolean result = javaClasses.stream()
                    .anyMatch(onlineClass -> onlineClass.isContainsToTitle("Test"));
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Spring 수업 중에 제목이 Spring 이 들어간 제목만 모아서 리스트로 변환")
    void springToList() {
        List<String> results = springClasses.stream()
                .filter(onlineClass -> onlineClass.isContainsToTitle("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        assertThat(results).hasSize(4);
    }

    private void initSpringClasses() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core",false));
        springClasses.add(new OnlineClass(5, "rest api development", false));
    }

    private void initJavaClasses() {
        javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test",true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation",true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11",false));
    }

    private void initStudyEvents() {
        studyEvents = new ArrayList<>();
        studyEvents.add(springClasses);
        studyEvents.add(javaClasses);
    }

}
