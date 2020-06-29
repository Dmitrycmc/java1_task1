package ru.gb.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        start("localhost", 3030);
    }

    public static void start(String domain, int port) {
        try {
            socket = new Socket(domain, port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        listenResponse();
        sendingFromConsole();
    }

    private static void sendingFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String msg;
        while (!(msg = scanner.nextLine()).equalsIgnoreCase("/end")) {
            send(msg);
        }
    }

    public static void listenResponse() {
        new Thread(() -> {
            try {
                while (true) {
                    String incomeMessage = in.readUTF();
                    if (incomeMessage.equalsIgnoreCase("/end")) {
                        break;
                    }
                    System.out.println("Принято сообщение: " + incomeMessage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void send(String str) {
        try {
            out.writeUTF(str);
            System.out.println("Отправлено сообщение: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
