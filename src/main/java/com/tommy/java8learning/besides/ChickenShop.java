package com.tommy.java8learning.besides;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("마늘간장")
public class ChickenShop {

//    public static void main(@Chicken String[] args) throws @Chicken RuntimeException{
    public static void main(String[] args) throws RuntimeException{
//        List<@Chicken String> names = Arrays.asList("hangyeol");

        // 어노테이션의 값을 가져오는 방법
        Chicken[] chickens = ChickenShop.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens)
                .forEach(c -> System.out.println(c.value()));

        // 컨테이너 타입으로 가져오는 방법
        ChickenContainer chickenContainer = ChickenShop.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value())
                .forEach(c -> System.out.println(c.value()));
    }

    // TYPE_PARAMETER 사용. 제네릭의 타입으로 들어가는 것 이다.
    // 설정을 하지 않을 경우 컴파일 에러가 나온다.
    /*
    static class FeelsLikeChicken<@Chicken T> {

        // <C>는 TYPE_PARAMETER, C는 TYPE 이다.
        // @Chicken 어노테이션을 TYPE_PARAMETER 에 붙일 수 있다. TYPE 에는 붙일 수 없다.
        public static <@Chicken C> void print(@Chicken C c) {
            System.out.println(c);
        }

    }**/
}
