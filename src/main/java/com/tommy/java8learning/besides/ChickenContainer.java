package com.tommy.java8learning.besides;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 컨테이너가 가지고 있는 Retention 과 Target 정보는 반드시 자기가 감쌀 어노테이션보다 같거나 넓어야 한다.
// 즉, ChickenContainer의 Retention 과 Target 의 범위는 @Chicken 보다 넓어야 한다.
@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChickenContainer {

    Chicken[] value();

}
