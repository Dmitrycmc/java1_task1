package ru.gb.java2.lesson8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

class Server {
    private int port;
    private MainWindow mainWindow;
    LinkedList<ClientHandler> clientHandlers = new LinkedList<>();

    public Server(int port) {
        this.port = port;
        start();
    }

    void broadcast(String message) {
        for (ClientHandler clientHandler: clientHandlers) {
            clientHandler.send(message);
        }
    }

    private void start() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Сервер запущен");
        mainWindow = new MainWindow(clientHandlers);

        while (true) {
            try {
                System.out.println("Ожидаем подключения...");
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился: " + socket);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                ClientHandler clientHandler = new ClientHandler(in, out, this, mainWindow);
                new Thread(() -> {
                    clientHandler.handleClient();
                    clientHandlers.remove(clientHandler);
                    mainWindow.refreshClients();
                }).start();
                clientHandlers.add(clientHandler);
                mainWindow.refreshClients();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
