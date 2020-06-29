package ru.gb.java2.lesson5;

import java.util.Arrays;

public class MainClass {
    static final int SIZE = 10000000;

    static ValueTransformer valueTransformer = (value, index) -> (float) (value * Math.sin(0.2f + index / 5) * Math.cos(0.2f + index / 5) * Math.cos(0.4f + index / 2));

    public static void main(String[] args) throws InterruptedException {
        float[] res1 = method1();
        float[] res2 = method2();

        boolean correct = Arrays.equals(res1, res2);
        System.out.println(correct ? "Results are equals" : "Results are not equal");
    }

    private static float[] method1() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);

        long t0 = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = valueTransformer.transform(arr[i], i);
        }

        long t1 = System.currentTimeMillis();

        long dt = t1 - t0;
        System.out.println("Method1: " + dt + "ms");

        return arr;
    }

    private static float[] method2() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);

        long t0 = System.currentTimeMillis();

        int halfSize = SIZE / 2;

        ArrayTransformer transformer1 = new ArrayTransformer(arr, 0, halfSize, valueTransformer);
        ArrayTransformer transformer2 = new ArrayTransformer(arr, halfSize, SIZE - halfSize, valueTransformer);

        Thread thread1 = new Thread(transformer1);
        Thread thread2 = new Thread(transformer2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long t1 = System.currentTimeMillis();

        long dt = t1 - t0;
        System.out.println("Method2: " + dt + "ms");

        return arr;
    }
}


