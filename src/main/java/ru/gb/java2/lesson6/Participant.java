package ru.gb.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

class Participant {
    static DataInputStream in;
    static DataOutputStream out;

    static void listen() {
        new Thread(() -> {
            try {
                String msg;
                do {
                    msg = in.readUTF();
                    System.out.println("Принято сообщение: " + msg);
                } while (!msg.equalsIgnoreCase("/end"));
            } catch (Exception e) {
                System.out.println("Соединение разорвано");
            } finally {
                System.exit(0);
            }
        }).start();
    }

    private static void send(String str) {
        try {
            out.writeUTF(str);
            System.out.println("Отправлено сообщение: " + str);
        } catch (IOException e) {
            System.out.println("Соединение разорвано");
            System.exit(0);
        }
    }

    static void sendingFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String msg;
        do {
            System.out.println("Введите сообщение:");
            msg = scanner.nextLine();
            send(msg);
        } while (!msg.equalsIgnoreCase("/end"));
        System.exit(0);
    }
}
