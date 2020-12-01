package com.tommy.java8learning.executor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFutureTest3 {

    /**
     * 2개의 Future 를 묶는다.
     * 서로 연관관계가 없을 경우 아래와 같이 사용 된다.
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> palantir = getStock("Palantir");
        CompletableFuture<String> snowFlake = getStock("Snowflake");

        // CompletableFuture<String> results = palantir.thenCombine(snowFlake, (pltr, snow) -> pltr + " " + snow);

        // Future 가 2개 이상일 때 모든 서브 태스크를 다 합쳐서 실행하는 방법은 아래와 같다.
        // 하지만 결과 값은 무의미 하다. 각 인자의 타입이 다를 수도 있고,
        // 결과를 가져올때 에러가 나서 못가져 왔을 수도 있다.
        // allOf 를 타입으로 정의하면 void 타입으로 정의가 된다.
        // 결과는 null 이 나온다.
        // CompletableFuture<Void> results = CompletableFuture.allOf(palantir, snowFlake)
        // .thenAccept(System.out::println);

        // 보완책은 아래와 같다.
        // 이렇게 하면 아무것도 블락킹이 되지 않는다.
        CompletableFuture<List<String>> results = getResults(palantir, snowFlake);

        results.get().forEach(System.out::println);
    }

    private static CompletableFuture<List<String>> getResults(CompletableFuture<String> palantir, CompletableFuture<String> snowFlake) {
        List<CompletableFuture<String>> futures = Arrays.asList(palantir, snowFlake);
        CompletableFuture[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);

        return CompletableFuture.allOf(futuresArray)
                .thenApply(result -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
    }

    private static CompletableFuture<String> getStock(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + Thread.currentThread().getName());
            return message;
        });
    }

}
