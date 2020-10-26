package com.tommy.java8learning;

@FunctionalInterface
public interface RunSomething {

    int doIt(int number);

    static void printName() {
        System.out.println("Hangyeol");
    }

    default void printAge() {
        System.out.println("27");
    }
}
