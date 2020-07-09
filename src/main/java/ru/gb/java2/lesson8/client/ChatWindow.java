package ru.gb.java2.lesson8.client;

import javax.swing.*;

public class ChatWindow extends JFrame {
    public ChatWindow(Client client) {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);

        setVisible(true);
        setEnabled(false);

        new AuthWindow(this, client);
    }
}
