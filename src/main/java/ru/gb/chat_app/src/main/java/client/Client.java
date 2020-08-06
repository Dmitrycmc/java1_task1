package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class Client {
    private DataInputStream in;
    private DataOutputStream out;

    Client(String domain, int port) {
        start(domain, port);
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

    String waitForAnswer() {
        String message = "";
        try {
            message = in.readUTF();
            System.out.println("Получено сообщение: " + message);
        } catch (Exception e) {
            System.out.println("Соединение разорвано");
            System.exit(0);
        }
        return message;
    }

    private void start(String domain, int port) {
        try {
            Socket socket = new Socket(domain, port);
            System.out.println("Соединение установлено!");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new ChatWindow(this);
        } catch (IOException e) {
            System.out.println("Не удалось подключиться!");
            System.exit(0);
        }
    }
}
