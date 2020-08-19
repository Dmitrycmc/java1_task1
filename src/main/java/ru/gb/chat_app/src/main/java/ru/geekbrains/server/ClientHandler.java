package ru.geekbrains.server;

import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class ClientHandler {
    private final Logger logger;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private JdbcClass jdbcClass;
    private MainWindow mainWindow;
    private String login;

    ClientHandler(DataInputStream in, DataOutputStream out, Server server, MainWindow mainWindow, JdbcClass jdbcClass, Logger logger) {
        this.in = in;
        this.out = out;
        this.server = server;
        this.mainWindow = mainWindow;
        this.jdbcClass = jdbcClass;
        this.logger = logger;
    }

    void send(String str) {
        try {
            out.writeUTF(str);
            logger.trace("Отправлено сообщение пользователю " + getLogin() + ": " + str);
        } catch (IOException e) {
            logger.info("Соединение разорвано");
        }
    }

    void handleClient() {
        try {
            authClient();
            mainWindow.refreshClients();
            logger.info("Клиент авторизовался");
            listen();
        } catch (IOException | SQLException e) {
            logger.info("Соединение разорвано");
        }
    }

    private void authClient() throws IOException, SQLException {
        String message;
        String[] words;
        while (true) {
            message = in.readUTF();
            logger.trace("Получено сообщение: " + message);
            words = message.split("\\s");
            if (jdbcClass.authUser(words[0], words[1])) {
                login = words[0];
                send("Success");
                return;
            }
            send("Invalid login or password");
        }
    }

    private void listen() throws IOException, SQLException {
        String message;
        while (true) {
            message = in.readUTF();
            logger.trace("Получено сообщение от " + getLogin() + ": " + message);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);

            if (message.startsWith("/changeLogin ")) {
                // change nick
                String[] words = message.split("\\s");
                String newLogin = words[1];
                login = jdbcClass.updateUsername(login, newLogin);
            } else if (message.startsWith("/w ")) {
                // private message
                String[] words = message.split("\\s");
                String[] pureMessageWords = new String[words.length - 2];
                System.arraycopy(words, 2, pureMessageWords, 0, words.length - 2);

                String pureMessage = String.join(" ", pureMessageWords);
                server.privateMessage(getLogin(), words[1],
                        String.format("(%s) private from %s to %s: %s", time, getLogin(), words[1], pureMessage)
                );
            } else {
                server.broadcast(String.format("(%s) %s: %s", time, getLogin(), message));
            }
        }
    }

    String getLogin() {
        return login == null ? "Unauthorized" : login;
    }
}
