package com.tommy.java8learning.executor;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableTest3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = CallableTest2.getCallable(2000L, "Hello");

        Callable<String> java = CallableTest2.getCallable(3000L, "Java");

        Callable<String> hangyeol = CallableTest2.getCallable(1000L, "Hangyeol");

        // 서버 3대가 있고, 같은 파일 A가 복사되어 모두 존재한다.
        // 3대의 서버로 나눈 이유는 특정 서버가 다운되도 서비스를 유지하기 위함이다.
        // 그리고 이 파일을 가져오는 경우가 있다.
        // 각 서버마다 리소스를 가져오는 시간이 다른데, 굳이 이 시간을 기다릴 필요가 없다.
        // 이때 사용되는 것이 invokeAny() 이다.

        // 블록킹 콜이라 바로 해당 타입으로 결과가 나온다.
        // 이 경우 ExecutorService 를 newFixedThreadPool 로 한 이유는,
        // SingleThread 일 경우 각 Callable 3개를 처리할 공간이 없어서 Hello 가 출력되기 때문이다.
        String result = executorService.invokeAny(Arrays.asList(hello, java, hangyeol));
        System.out.println(result); // 가져오는 시간이 가장 짧은 Hangyeol 호출

        executorService.shutdown();
    }
}
