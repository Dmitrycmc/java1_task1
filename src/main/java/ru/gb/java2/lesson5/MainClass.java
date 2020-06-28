package ru.gb.java2.lesson5;

import java.util.Arrays;

public class MainClass {
    static final int SIZE = 10000000;

    public static void main(String[] args) throws InterruptedException {
        method1();
        method2();
    }

    private static void method1() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);

        long t0 = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long t1 = System.currentTimeMillis();

        long dt = t1 - t0;
        System.out.println(dt);
    }

    private static void method2() throws InterruptedException {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);

        long t0 = System.currentTimeMillis();

        int halfSize = SIZE / 2;
        float[] arr1 = new float[halfSize];
        float[] arr2 = new float[SIZE - halfSize];

        System.arraycopy(arr, 0, arr1, 0, halfSize);
        System.arraycopy(arr, halfSize, arr2, 0, SIZE - halfSize);

        ArrayTransformer transformer1 = new ArrayTransformer(arr1, 0);
        ArrayTransformer transformer2 = new ArrayTransformer(arr2, halfSize);

        Thread thread1 = new Thread(transformer1);
        Thread thread2 = new Thread(transformer2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.arraycopy(arr1, 0, arr, 0, halfSize);
        System.arraycopy(arr2, 0, arr, halfSize, SIZE - halfSize);

        long t1 = System.currentTimeMillis();

        long dt = t1 - t0;
        System.out.println(dt);
    }
}


