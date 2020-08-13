package ru.gb.java3.lesson4;

/*
    1. Создать три потока, каждый из которых выводит
    определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
    Используйте wait/notify/notifyAll.
*/
public class MainClass {
    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        MainClass w = new MainClass();
        int n = 5;
        Thread t1 = new Thread(() -> {
            w.print('A', n);
        });
        Thread t2 = new Thread(() -> {
            w.print('B', n);
        });
        Thread t3 = new Thread(() -> {
            w.print('C', n);
        });
        t1.start();
        t2.start();
        t3.start();
    }

    private void print(char c, int n) {
        synchronized (mon) {
            try {
                for (int i = 0; i < n; i++) {
                    while (currentLetter != c) {
                        mon.wait();
                    }
                    System.out.print(currentLetter);
                    currentLetter = (char)(((currentLetter + 1) - 'A') % 3 + 'A');
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


