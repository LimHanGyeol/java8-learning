package com.tommy.java8learning.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest2 {

    /**
     * 2개의 Future 를 묶는다.
     * 서로 연관관계가 있을 경우 아래와 같이 사용 된다.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = getHello();

        CompletableFuture<String> future = hello.thenCompose(CompletableFutureTest2::getWorld);

        System.out.println(future.get());
    }

    private static CompletableFuture<String> getHello() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + " World";
        });
    }

}
