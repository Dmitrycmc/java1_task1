package ru.gb.java2.lesson8.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientHandler {
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String login;

    public ClientHandler(DataInputStream in, DataOutputStream out, Server server) {
        this.in = in;
        this.out = out;
        this.server = server;
    }

    void send(String str) {
        try {
            out.writeUTF(str);
            System.out.println("Отправлено сообщение: " + str);
        } catch (IOException e) {
            System.out.println("Соединение разорвано");
            System.exit(0);
        }
    }

    public void handleClient() {
        new Thread(() -> {
            try {
                authClient();
                System.out.println("Клиент авторизовался");
                listen();
            } catch (IOException e) {
                System.out.println("Соединение разорвано");
            }
        }).start();
    }

    private void authClient() throws IOException {
        String msg;
        String[] words;
        while (true) {
            msg = in.readUTF();
            System.out.println("Получено сообщение: " + msg);
            words = msg.split("\\s");
            if (Authenticator.auth(words[0], words[1])) {
                login = words[0];
                send("Success");
                return;
            }
            send("Invalid login or password");
        }

    }

    private void listen() throws IOException {
        String msg;
        while (true) {
            msg = in.readUTF();
            System.out.println("Получено сообщение: " + msg);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);

            server.broadcast(String.format("(%s) %s: %s", time, login, msg));
        }
    }
}
