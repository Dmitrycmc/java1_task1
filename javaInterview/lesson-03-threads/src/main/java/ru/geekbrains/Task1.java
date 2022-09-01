package ru.geekbrains;

public class Task1 {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread ping = new Thread(new PingPongThread("Ping", lock));
        Thread pong = new Thread(new PingPongThread("Pong", lock));

        ping.start();
        pong.start();
    }
}
