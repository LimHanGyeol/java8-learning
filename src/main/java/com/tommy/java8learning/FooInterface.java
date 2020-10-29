package com.tommy.java8learning;

public interface FooInterface {

    String printName();

    String getName();

    /**
     * @implSpec 이 추상 메서드는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     * Default 메서드도 구현체에서 오버라이딩 가능하다.
     */
    default String printNameUpperCase() {
        return getName().toUpperCase();
    }

    static String printAnything() {
        return "Foo";
    }

}
