package com.tommy.java8learning;

public class Foo {

    public static void main(String[] args) {
        // 익명 내부 클래스 Anonymous Inner Class
        RunSomething runSomething = (number) -> number + 10;
        System.out.println(runSomething.doIt(1));
    }
}
