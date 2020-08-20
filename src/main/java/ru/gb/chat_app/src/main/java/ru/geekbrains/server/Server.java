package ru.geekbrains.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server {
    private int port;
    private MainWindow mainWindow;
    private LinkedList<ClientHandler> clientHandlers = new LinkedList<>();
    private JdbcClass jdbcClass;
    private final Logger logger = LogManager.getLogger(Server.class);

    Server(int port) {
        this.port = port;
        jdbcClass = new JdbcClass(logger);
        start();
    }

    void broadcast(String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.send(message);
        }
    }

    void privateMessage(String userLoginFrom, String userLoginTo, String message) {
        for (ClientHandler clientHandler : clientHandlers) {
            if (clientHandler.getLogin().equals(userLoginFrom) || clientHandler.getLogin().equals(userLoginTo))
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
        logger.info("Сервер запущен");
        mainWindow = new MainWindow(clientHandlers);

        ExecutorService executorService = Executors.newCachedThreadPool();

        while (true) {
            try {
                logger.info("Ожидаем подключения...");
                Socket socket = serverSocket.accept();
                logger.info("Клиент подключился: " + socket);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                ClientHandler clientHandler = new ClientHandler(in, out, this, mainWindow, jdbcClass, logger);
                executorService.execute(() -> {
                    clientHandler.handleClient();
                    clientHandlers.remove(clientHandler);
                    mainWindow.refreshClients();
                });
                clientHandlers.add(clientHandler);
                mainWindow.refreshClients();
            } catch (IOException e) {
                e.printStackTrace();
                logger.fatal("Выброшено исключение");
            }
        }
    }
}
