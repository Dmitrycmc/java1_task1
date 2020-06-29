package ru.gb.java2.lesson6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static DataInputStream in;
    private static DataOutputStream out;


    public static void main(String[] args) {
        start(3030);
    }

    private static void listenRequest() {
        new Thread(() -> {
            try {
                while (true) {
                    String str = in.readUTF();
                    if (str.equalsIgnoreCase("/end")) {
                        break;
                    }
                    System.out.println("Принято сообщение: " + str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void sendingFromConsole() {
        Scanner scanner = new Scanner(System.in);
        String msg;
        while (!(msg = scanner.nextLine()).equalsIgnoreCase("/end")) {
            send(msg);
        }
    }

    public static void send(String str) {
        try {
            out.writeUTF(str);
            System.out.println("Отправлено сообщение: " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();

            System.out.println("Клиент подключился");
            System.out.println(socket.toString());

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            listenRequest();
            sendingFromConsole();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
