package ru.gb.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server extends Participant {
    private static Socket socket;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {
        start(3030);
    }

    static void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            listen();
            sendingFromConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
