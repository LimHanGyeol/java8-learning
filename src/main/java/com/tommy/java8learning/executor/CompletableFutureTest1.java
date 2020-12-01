package com.tommy.java8learning.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureTest1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = new CompletableFuture<>();
        future1.complete("hangyeol"); // 기본 값
        System.out.println(future1.get());

        // 정적 팩토리 메서드를 이용하여 같은 기능을 구현할 수 있다.
        CompletableFuture<String> future2 = CompletableFuture.completedFuture("hangyeol");
        System.out.println(future2.get());

        // 리턴이 있는 작업, 없는 작업의 형태로도 구현 가능하다.
        // 리턴이 없는 작업은 아래와 같다. 하지만 future 만 정의한 것이기 때문에 아무 일도 일어나지 않는다.
        // get 을 해줘야 한다.
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future3.get();

        // 리턴이 있는 작업은 아래와 같다.
        // 여기에서 비동기적으로 콜백을 줄 수 있다.
        // thenApply 로 받은 결과 값을 다르게 처리할 수 있다. function 이 들어간다.
        // 기존 Future 에서는 get 호출 전에 콜백으로 로직 처리가 불가능 했다.
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            return "Hello - 4 " + Thread.currentThread().getName();
        }).thenApply(String::toUpperCase);

        // thenAccept 로 Consumer 타입의 콜백 호출도 가능하다.
        // 이 경우 void 로 처리가 되기 때문에 "CompletableFuture<Void>" 타입으로 바꿔야 한다.
        // .thenAccept(result -> {
        // System.out.println(Thread.currentThread().getName());
        // });

        // Return 을 받을 필요 없이 무엇인가를 하기만 한다면
        // thenRun 을 사용할 수 있다.
        // thenRun 은 결과값 참고를 하지 못한다. 인자로 Runnable 이 온다.
        // .thenRun(() -> {
        // System.out.println(Thread.currentThread().getName());
        // });

        // 비동기적으로 작업을 처리하는데 스레드풀을 만들지 않았다.
        // 이는 Fork/Join Pool 을 사용하기 때문이다.
        // Fork/Join Pool 은 JDK 1.7 에 구현 됐다.
        // 이도 Executor 의 구현체 중 하나이다.
        // 내부적으로 마지막에 들어온게 먼저 나가는 Dequeue 를 사용한다.
        // 스레드가 할일이 없는 경우 Dequeue 에서 할 일을 가져와서 처리하는 프레임워크이다.

        // 중요한 것은 별다른 Executor 을 사용하지 않아도,
        // 내부적으로 Fork/Join Pool 에 있는 commonPool 을 사용하게 된다.
        // 원한다면 Thread Pool 을 만들어서 사용할 수 있다.
        // 만든 Executors.nnnnn..ThreadPool 을
        // supplyAsync, runAsync 메서드를 호출할 때 두번째 인자로 줄 수 있다.
        // 콜백이 사용되는 then ...Async 메서드에서도 두번째 인자로 사용할 수 있다.

        System.out.println(future4.get());
    }

}
