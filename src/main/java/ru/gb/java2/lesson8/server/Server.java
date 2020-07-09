package ru.gb.java2.lesson8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

class Server {
    private int port;
    LinkedList<ClientHandler> clientHandlers = new LinkedList<>();

    public Server(int port) {
        this.port = port;
        start();
    }

    public void start() {
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
                System.out.println("Клиент подключился: " + socket);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                new Thread(() -> clientHandlers.add(new ClientHandler(in, out))).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
