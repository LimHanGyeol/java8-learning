package com.tommy.java8learning;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Foo {

    public static void main(String[] args) {
        // 익명 내부 클래스 Anonymous Inner Class
        RunSomething runSomething = (number) -> number + 10;
        System.out.println(runSomething.doIt(1));

        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11; // 스콥 쉐도잉
                System.out.println(baseNumber); // 11이 찍힘
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) { // 스콥 쉐도잉
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer printInt = (number) -> {
            System.out.println(number + baseNumber);
        };
        printInt.accept(5);
    }
}
