package com.tommy.java8learning.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest4 {

    /**
     * 2개의 Future 를 묶는다.
     * 서로 연관관계가 없고 먼저 끝나는 결과를 가져올 경우 아래와 같이 사용 된다.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = getHello();
        CompletableFuture<String> world = getWorld();

        // System.out.println(hello.get());
        System.out.println(world.get());

        // CompletableFuture<Void> future = CompletableFuture.anyOf(hello, world)
        // .thenAccept(System.out::println);

        // future.get();
    }

    private static CompletableFuture<String> getHello() {
        boolean throwError = true;
        return CompletableFuture.supplyAsync(() -> {
            // 비동기적으로 동작하는 태스크 안에서 에러가 발생할 경우
            // 내용을 정의하고 .exceptionally 를 사용한다.
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(exception -> {
            System.out.println(exception);
            return "Exceptionally Error!";
        });
    }

    private static CompletableFuture<String> getWorld() {
        // 비동기적으로 동작하는 태스크 안에서 에러가 발생할 경우 조금 더 일반적인 메서드가 있다.
        // handle 은 정상적으로 종료 됐을때와 에러가 발생했을 때 모두 사용할 수 있다.
        // 인자는 BiFunction 을 받는다.
        boolean throwError = true;
        return CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        }).handle((result, exception) -> {
            if (exception != null) {
                System.out.println(exception);
                return "Handle Error!";
            }
            return result;
        });
    }

}
