package ru.geekbrains;

public class PingPongThread implements Runnable {
    private String message;
    private Object lock;

    public PingPongThread(String message, Object lock) {
        this.message = message;
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                tick();
                lock.notify();

                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void tick() {
        System.out.println(this.message);
    }
}
