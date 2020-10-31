package com.tommy.java8learning.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalTest {

    private List<OnlineClass> springClasses;

    @BeforeEach
    void setUp() {
        springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));
    }

    @Test
    @DisplayName("spring 으로 시작하는 수업이 있는지 확인")
    @Description("Java11 부터는 result.isEmpty()로 거짓일 경우의 리턴도 제공 한다.")
    void isContainsToSpring() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(onlineClass -> onlineClass.isStartsWithToTitle("spring"))
                .findFirst();
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Optional 이 없으면 ... orElse(인스턴스 생성)")
    void optionalOrElse() {
        OnlineClass result = springClasses.stream()
                .filter(onlineClass -> onlineClass.isStartsWithToTitle("jpa"))
                .findFirst()
                .orElseGet(OptionalTest::createStudy); // createStudy() 메서드가 null 일 경우에만 호출됨.
//                .orElse(createStudy()); // createStudy() 메서드가 무조건 호출이됨.
//                .orElseThrow(IllegalArgumentException::new); // exception throw

        assertThat(result.getTitle()).isEqualTo("New Study Is Jpa");
    }

    private static OnlineClass createStudy() {
        return new OnlineClass(10, "New Study Is Jpa", false);
    }

    @Test
    @DisplayName("Optional 을 리턴하여 Optional<Optional<Type>>이 될 경우 flatMap 으로 타입 축약")
    void optionalToFlatMap() {
        Optional<OnlineClass> result = springClasses.stream()
                .filter(onlineClass -> onlineClass.isStartsWithToTitle("spring"))
                .findFirst();

        /*
        아래와 같이 Optional 을 리턴하는 경우 계속 꺼내 써야해서 좋은 사용방법이 아니다.
        이럴 경우 flatMap 을 사용하여 Optional 을 축약해준다.
        Optional<Optional<Progress>> progress = result.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElseThrow();
        progress1.map(...);
        Optional 과 stream 의 flatMap 은 다르다.
        stream 에서의 flatMap input 은 하나인데 output 이 여럿일 때 사용할 수 있다.
         */

        Optional<Progress> progress = result.flatMap(OnlineClass::getProgress);
    }
}
