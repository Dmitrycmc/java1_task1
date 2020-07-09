package ru.gb.java2.lesson8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClientHandler {
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
        handleClient();
    }

    private void send(String str) {
        try {
            out.writeUTF(str);
            System.out.println("Отправлено сообщение: " + str);
        } catch (IOException e) {
            System.out.println("Соединение разорвано");
            System.exit(0);
        }
    }

    private void handleClient() {
        authClient();
        System.out.println("Клиент авторизовался");
        // todo: handle chat
    }

    private void authClient() {
        try {
            String msg;
            String[] words;
            while (true) {
                msg = in.readUTF();
                words = msg.split("\\s");
                if (Authenticator.auth(words[0], words[1])) {
                    send("Success");
                    break;
                }
                send("Invalid login or password");
            }
        } catch (Exception e) {
            System.out.println("Соединение разорвано");
        }
    }
}
