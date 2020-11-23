package com.tommy.java8learning.besides;

import java.lang.annotation.*;

// TYPE_USE 는 TYPE_PARAMETER 를 포함한다.
// 타입을 선언하는 모든 곳에 해당 어노테이션을 사용할 수 있다.
@Target(ElementType.TYPE_USE)
@Retention(RetentionPolicy.RUNTIME)
// 여러 어노테이션을 감싸고 있을 컨테이너 어노테이션 타입을 선언해주어야 한다.
@Repeatable(ChickenContainer.class)
public @interface Chicken {

    String value();

}
