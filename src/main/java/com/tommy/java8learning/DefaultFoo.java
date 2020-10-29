package com.tommy.java8learning;

public class DefaultFoo implements FooInterface, Bar {

    private final String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public String printName() {
        return this.name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @implNote
     * 2개의 인터페이스를 상속할때 같은 default 메서드가 중복될 경우 충돌이 발생한다.
     * 이럴 경우 구현체에서 재정의를 해줘야 한다.
     */
    @Override
    public String printNameUpperCase() {
        return this.name.toUpperCase();
    }
}
