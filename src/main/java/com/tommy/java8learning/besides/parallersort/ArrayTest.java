package com.tommy.java8learning.besides.parallersort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class ArrayTest {

    private static final Random random = new Random();

    /**
     * Java 8 배열에 새로 들어온 기능.
     * 정렬을 할 때 Fork/Join 프레임워크를 사용하여 병렬적으로 정렬을 할 수 있다.
     * 알고리즘의 효율이 바뀐 것은 아니지만 ParallelSort 라는 메서드가 추가 되었다.
     * 여러 스레드가 분산되어 처리하기 때문에 조금 더 빠르게 정렬을 할 수 있다.
     * 정렬 속도는 거진 ParallelSort 가 더 빠르지만
     * CPU 등의 리소스와 정렬하는 배열의 크기에 따라 달라질 수 있다.
     */
    public static void main(String[] args) {
        int size = 1500;
        int[] numbers = new int[size];

        initArrays(size, numbers);

        long start = System.nanoTime();
        Arrays.sort(numbers); // Quicksort 사용 O(n log(n)), Single Thread 사용
        System.out.println("serial sorting took " + (System.nanoTime() - start));

        initArrays(size, numbers);

        start = System.nanoTime();
        Arrays.parallelSort(numbers);
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }

    private static void initArrays(int size, int[] numbers) {
        IntStream.range(0, size)
                .forEach(i -> numbers[i] = random.nextInt());
    }

}
