package ru.geekbrains.client;

import javax.swing.*;
import java.awt.*;

class ChangeNickWindow extends JFrame {
    private Client client;
    private String login;
    private JTextField loginField;
    ChatWindow chatWindow;

    private void submit() {
        login = loginField.getText();
        client.send("/changeLogin " + login);
        chatWindow.setEnabled(true);
        chatWindow.setTitle("Чат: " + login);
        setVisible(false);
    }

    private void cancel() {
        chatWindow.setEnabled(true);
        setVisible(false);
    }

    ChangeNickWindow(Client client, ChatWindow chatWindow) {
        this.client = client;
        this.chatWindow = chatWindow;

        setTitle("Changing nick");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(300, 300, 400, 100);
        setResizable(false);

        setLayout(new GridLayout(2, 2));

        add(new JLabel("New login:"));
        loginField = new JTextField();
        loginField.addActionListener(e -> submit());
        add(loginField);

        JButton sendButton = new JButton("Change");
        sendButton.addActionListener(e -> submit());
        add(sendButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> cancel());
        add(cancelButton);

        setVisible(true);
    }
}
