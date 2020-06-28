package ru.gb.java2.lesson5;

import java.util.Arrays;

public class MainClass {
    static final int SIZE = 10000000;

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
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
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
        float[] arr1 = new float[halfSize];
        float[] arr2 = new float[SIZE - halfSize];

        System.arraycopy(arr, 0, arr1, 0, halfSize);
        System.arraycopy(arr, halfSize, arr2, 0, SIZE - halfSize);

        ArrayTransformer transformer1 = new ArrayTransformer(arr1, (v, i) -> (float)(v * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2)));
        ArrayTransformer transformer2 = new ArrayTransformer(arr2, (v, i) -> (float)(v * Math.sin(0.2f + (halfSize + i) / 5) * Math.cos(0.2f + (halfSize + i) / 5) * Math.cos(0.4f + (halfSize + i) / 2)));

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
        System.out.println("Method2: " + dt + "ms");

        return arr;
    }
}


