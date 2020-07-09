package ru.gb.java2.lesson8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

class Server {
    private final static int PORT = 8554;
    static LinkedList<ClientHandler> clientHandlers = new LinkedList<>();

    public static void main(String[] args) {
        start(PORT);
    }

    public static void start(int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сервер запущен");

        while (true) {
            try {
                System.out.println("Ожидаем подключения...");
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                new Thread(() -> clientHandlers.add(new ClientHandler(in, out))).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
