package com.tommy.java8learning.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // executorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(ExecutorTest.getRunnable("Hello"), 1,2, TimeUnit.SECONDS);

        // executorService.shutdown();
    }

}
