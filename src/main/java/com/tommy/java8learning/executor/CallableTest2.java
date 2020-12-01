package com.tommy.java8learning.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableTest2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = getCallable(2000L, "Hello");

        Callable<String> java = getCallable(3000L, "Java");

        Callable<String> hangyeol = getCallable(1000L, "Hangyeol");

        // Callable 을 묶어서 사용할 수 있다.
        // invokeAll 은 묶인 Callable 을 전부 기다리기 때문에 한번에 출력이 된다.
        // 사용 사례는 주식 주가를 조회하여 내가 가진 모든 주식의 현재 시가를 가져와,
        // 현재 보유 총 자산을 계산할때이다.
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, hangyeol));

        printFuture(futures);

        executorService.shutdown();
    }

    public static Callable<String> getCallable(long millis, String message) {
        return () -> {
            Thread.sleep(millis);
            return message;
        };
    }

    private static void printFuture(List<Future<String>> futures) throws InterruptedException, ExecutionException {
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }
    }

}
