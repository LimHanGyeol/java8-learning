package com.tommy.java8learning.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void main(String[] args) {
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("Hello"));
        executorService.submit(getRunnable("Hangyeol"));
        executorService.submit(getRunnable("The"));
        executorService.submit(getRunnable("Java8"));
        executorService.submit(getRunnable("Thread"));

        executorService.shutdown();
        // executorService.shutdownNow();
    }

    private static Runnable getRunnable(String message) {
        return () -> {
            System.out.println(message + Thread.currentThread().getName());
        };
    }

}
