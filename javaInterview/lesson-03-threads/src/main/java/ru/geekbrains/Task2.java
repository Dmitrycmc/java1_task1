package ru.geekbrains;

public class Task2 {
    private static final int N = 1000;
    private static final int M = 1000;

    static Counter cnt = new ConcurrentCounter();

    public static void main(String[] args) throws InterruptedException {
        Thread[] arr = new Thread[N];

        for (int i = 0; i < N; i++) {
            arr[i] = new Thread(Task2::f);
            arr[i].start();
        }
        for (int i = 0; i < N; i++) {
            arr[i].join();
        }

        System.out.println(cnt.get() + " / " + N * M);
        System.out.println((N * M - cnt.get()) + " / " + N * M + " lost");
    }

    private static void f() {
        for (int i = 0; i < M; i++) {
            cnt.inc();
        }
    }
}
