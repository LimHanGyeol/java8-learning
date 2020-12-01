package com.tommy.java8learning.executor;

import java.util.concurrent.*;

public class CallableTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        // System.out.println(helloFuture.isDone());
        System.out.println("Started!!");

        // System.out.println(helloFuture.get()); // 블록킹 콜콜
        helloFuture.cancel(true); // cancel 을 호출하면 취소된 작업이기 때문에 get 으로 값을 가져올 수 없다.

        System.out.println(helloFuture.isDone());
        System.out.println("End!!");
        executorService.shutdown();

    }
}
