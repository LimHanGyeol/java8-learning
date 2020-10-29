package com.tommy.java8learning;

public interface Bar {

    default String printNameUpperCase() {
        return "Bar";
    }

}
