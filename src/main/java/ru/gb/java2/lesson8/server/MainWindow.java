package ru.gb.java2.lesson8.server;

import javax.swing.*;
import java.util.LinkedList;

class MainWindow extends JFrame {
    private LinkedList<ClientHandler> clientHandlers;
    private JTextArea clientsArea;

    MainWindow(LinkedList<ClientHandler> clientHandlers) {
        this.clientHandlers = clientHandlers;

        setTitle("Сервер чата");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);

        clientsArea = new JTextArea();
        clientsArea.setEditable(false);
        add(clientsArea);

        setVisible(true);
    }

    void refreshClients() {
        clientsArea.setText("");
        for (ClientHandler clientHandler : clientHandlers) {
            clientsArea.setText(clientsArea.getText() + (clientsArea.getText().equals("") ? "" : '\n') + clientHandler.getLogin());
        }
    }
}
