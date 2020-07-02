package ru.gb.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class Client extends Participant {
    private static Socket socket;

    public static void main(String[] args) {
        start("localhost", 3030);
    }

    private static void start(String domain, int port) {
        try {
            socket = new Socket(domain, port);
            System.out.println("Соединение установлено!");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Не удалось подключиться!");
            System.exit(0);
        }

        listen();
        sendingFromConsole();
    }
}
